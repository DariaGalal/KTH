<!DOCTYPE html>
<?php session_start(); ?>
<html lang="en">
<head>
  <meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../CSS/reset.css">
<link rel="stylesheet" type="text/css" href="../CSS/homepage.css">
  <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Maven+Pro" rel="stylesheet">
    <title>
      Tasty Recipes
    </title>
</head>
  <body>
    <div>
      <div class="icon">
    <h1>
      <a href="index.php">Tasty Recipes</a>
      <div class="loginborder">
        <?php if(!isset($_SESSION['logged-in'])){ ?>
      <a class="login" href="../Login/login.html">Login</a>
    </div>
  <?php }else{ ?>
    <a class="login" href="../login/logout.php">Logout</a>
    </div>
  <?php } ?>
      <br>
        <a href="index.php" class="home">Home</a>
          <a href="calendar.php" class="calendar">Calendar</a>
          </h1>
    </div>
    </div>
    <?php if(isset($_SESSION['logged-in'])){ ?>
    <p>Welcome <?php echo $_SESSION['user'] ?>! Make sure to leave a comment on your favourite dishes!
    </p>
  <?php }else{ ?>
    <p>Welcome! Feel free to check out any recipes down below by clicking on its image or why not check out the
      <a href="calendar.html">calendar?</a>
    </p>
  <?php } ?>
      <div>
        <p>
        <a href="../HTML/pancakes.php">
      <img alt="Pancakes" src="../pictures/pancakes.jpg">
    </a>
      <a href="meatballs.php">
      <img alt="Meatballs" src="../pictures/meatballs.jpg">
    </a>
    <a href="calendar.php">
    <img alt="Calendar" src="../pictures/calendar.png">
  </a>
  </p>
    </div>
  </body>
</html>
