//users.js
var express = require('express');
var router = express.Router();
var DBHandler = require("../models/dbhandler");
var User = require("../models/user");
var bcrypt = require('bcrypt');
var jwt = require('jsonwebtoken');

if (typeof localStorage === "undefined" || localStorage === null) {
  var LocalStorage = require('node-localstorage').LocalStorage;
}

//Register
router.get('/register', function(req, res){
	console.log("get register");
	res.render('register');
});

//Login
router.get('/login', verifyToken, function(req, res){
	res.render('login');
});

//logout
router.get('/logout', function(req, res){
  var localStorage = new LocalStorage('./scratch');
	localStorage.removeItem('token');
	localStorage.removeItem('user');
	res.redirect('login');
})

//Register user
router.post('/register', function(req, res){
	var firstName = req.body.firstName;
	var lastName = req.body.lastName;
	var email = req.body.email;
	var password = req.body.password;
	var confirmPassword = req.body.confirmPassword;

	if(password === confirmPassword){
		var dbhandler = new DBHandler();
		dbhandler.RegisterUser(email, password, firstName, lastName, function (err){
			if(err){
				res.render('register', {existingUser: true});
			} else{
				res.redirect('login');
			}
		});
	}
});

router.post('/login', function (req, resp) {
	var email = req.body.email;
	var password = req.body.password;
  var localStorage = new LocalStorage('./scratch');
  console.log(email + " " + password);
	var dbhandler = new DBHandler();
	dbhandler.RetrieveUser(email, function(error, user){
		if(error) console.log(error);
		if(user != null){
			bcrypt.compare(password, user.getPassword(), function(err, res) {
        if(err) console.log(err);
			  if(res) {
					jwt.sign({id: user.getId(), firstname: user.getFirstName(), username: user.getEmail(), password: user.getPassword()}, 'secretkey', (error, token) => {
						if(!error){
							localStorage.setItem('token', token);
							localStorage.setItem('user', user.getId());
              console.log("logged in: " + user.getEmail() + ", " + user.getId());
							resp.redirect('../index');
						}
					});
			  } else {
				 resp.render('login', {wrongPassword: true});
			  }
			});
		}else{
      console.log("couldnt log in");
			resp.render('login', {falseUser:true});
		}
	});
});

router.get('/users', function(req, res){
	var dbhandler = new DBHandler();
	dbhandler.RetrieveUsers(function(error, users){
		if(error) console.error(error);
		if(users != null){
			var userOut = "<div class='list-group'>";
			users.forEach(function(entry) {
				userOut = userOut + '<a href="#" class="list-group-item list-group-item-action">' + entry.USERNAME + '</a>';
			});
			res.render('showusers', {userOut: userOut});
		}else{
			res.render('login', {falseUser:true});
		}
	});
});

//Verify token
function verifyToken(req, res, next){
  var localStorage = new LocalStorage('./scratch');
	const token = localStorage.getItem('token');
	if(token !== null){
		jwt.verify(token, 'secretkey', function(error, decoded) {
			if(error) {
				//Malformed token
				res.render('login');
			}else{
				res.redirect('../index');
			}
		});
	}else{
		//Forbidden
		res.render('login');
	}
}


module.exports = router;
