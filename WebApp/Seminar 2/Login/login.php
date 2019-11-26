<?php
  //creating hard-coded user info
  $username = "pelle";
  $password = "1234";

//checking if the form is filled
   if(isset($_POST['submit']) && !empty($_POST['uname']) && !empty($_POST['psw']))
      session_start();
     //checking if the information is equal to the hard-coded userinfo
     if($_POST['uname'] == $username && $_POST['psw'] == $password)
     {
       $_SESSION['logged-in'] = true;
       $_SESSION['user'] = $username;
       header('Location: ../HTML/index.php');
       exit();
     }
     else {
       //alert the user when entering wrong info
       $_SESSION['logged-in'] = false;
       echo "<script>alert('username or password is incorrect')</script>";
       echo "<script>location.href='../login/login.html'</script>";
     }
?>
