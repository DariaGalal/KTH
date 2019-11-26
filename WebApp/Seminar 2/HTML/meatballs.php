<!DOCTYPE html>
<?php session_start();
      require_once '../comment/comment.php';
 ?>
<html lang="en">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="../CSS/reset.css">
  <link rel="stylesheet" type="text/css" href="../CSS/recipe.css">
  <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Amatic+SC" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Maven+Pro" rel="stylesheet">
      <title>Meatballs</title>
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
      <a href="pancakes.php" class="toPancake">To pancakes!</a>
        <a href="index.php" class="home">Home</a>
          <a href="calendar.php" class="calendar">Calendar</a>
          </h1>
    </div>
    </div>
<p class="food-name">Meatballs</p>
    <br>
    <div class="content">
      <div class="left-side">
        <div class="food-picture">
          <img alt="meatballs" src="../pictures/meatballs.jpg">
          <p class="info">Serving: 8</p>
        </div>
        <div class="Ingredients">
          <p class="ing">INGREDIENTS</p>
          <p>1 pound ground beef</p>
          <p>1/2 pound ground veal</p>
          <p>1/2 pound ground pork</p>
          <p>2 cloves of garlic, minced</p>
          <p>2 eggs</p>
          <p>1 cup freshly grated Romano cheese</p>
          <p>1 1/2 tablesppons chopped Italian flat leaf parsley</p>
          <p>Salt and pepper to taste</p>
          <p>2 cups stale Italian bread, crumbled</p>
          <p>1 1/2 cups lukewarm water</p>
          <p>1 cup olive oil</p>

          <div class="Instructions">
            <p class="ing">INSTRUCTIONS</p>
            <p>Combine beef, veal, and pork in a large bowl. Add garlic, eggs, cheese, parsley, salt and pepper.</p>
            <br>
            <p>Blend bread crumbs into meat mixture. Slowly add the water 1/2 cup at a time. The mixture should be very moist but still hold its shape if rolled into meatballs.
              (I usually use about 1 1/4 cups of water). Shape into meatballs.</p>
              <br>
            <p>Heat olive oil in a large skillet. Fry meatballs in batches. When the meatball is very brown and slightly crisp remove from the heat and drain on a paper towel.
              (If your mixture is too wet, cover the meatballs while they are cooking so that they hold their shape better).</p>
            </div>
          </div>
      </div>
      <div class="right-side">
        <p class="ing">COMMENTS</p>
        <br>
        <?php if(isset($_SESSION['logged-in'])){ ?>
        <form action="../comment/user-comment.php" method='post'>
          <br>
          <textarea placeholder="Enter your comment here.." id="entry" rows="4" cols="50" name="msg">
            </textarea>
            <br>
            <input type="hidden" name="dish" value="meatballs"/>
              <input type="submit">
              </form>
              <?php } ?>
                <br>
                <div class="user-comments">
                  <?php
                    $filename = __DIR__ . '/meatball-comments.txt';
                    $entries = explode(";\n", file_get_contents($filename));
                    for ($i = count($entries) - 1; $i >= 0; $i--) {
                      $entry = unserialize($entries[$i]);
                      if ($entry instanceof comment && $entry->isDeleted() == 0) {
                        echo ("<p class='author'>" . $entry->getUserName() . ":</p>" . "<p class='time'> (" . $entry->getTimestamp() . ")</p>");
                        echo("<p class='entry'>");
                        echo(nl2br($entry->getMsg()));
                        echo ("</p>");
                        echo "<br>";
                        if(isset($_SESSION['logged-in'])){
                          if($entry->getUserName() === $_SESSION['user']) {
                            echo("<form action='../comment/delete-comment.php'>");
                            echo("<input type='hidden' name='timestamp' value='" .
                            $entry->getTimestamp() . "'/>");
                            echo("<input type='hidden' name='dish' value='" .  $entry->getDish() . "'/>");
                            echo("<input type='submit' value='Delete'/>");
                            echo("</form>");
                        }
                      }
                    }
                  }
                    ?>
                  </div>
  </body>
</html>
