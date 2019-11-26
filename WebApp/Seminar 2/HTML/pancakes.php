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
      <title>Pancakes</title>
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
      <a href="meatballs.php" class="toMeatball">To meatballs!</a>
        <a href="index.php" class="home">Home</a>
          <a href="calendar.php" class="calendar">Calendar</a>
          </h1>
        </div>
        </div>
    <p class="food-name">Pancakes</p>
        <br>
        <div class="content">
          <div class="left-side">
            <div class="food-picture">
              <img alt="meatballs" src="../pictures/pancakes.jpg">
              <p class="info">Serving: 8</p>
              <div class="Ingredients">
                <p class="ing">INGREDIENTS</p>
                <p>1 1/2 cups all-purpose flour</p>
                <p>3 1/2 teaspoon baking poweder</p>
                <p>1 teaspoon salt</p>
                <p>1 tablespoon white sugar</p>
                <p>1 1/4 cups milk</p>
                <p>1 egg</p>
                <p>3 tablespoons butter, melted</p>
                <p>1 teaspoon vanilla</p>

                <div class="Instructions">
                  <p class="ing">INSTRUCTIONS</p>
                  <p>In a large bowl, sift together the flour, baking powder, salt and sugar. Make a well in the center and pour in the milk, egg, melted butter and vanilla; mix until smooth.</p>
                    <br>
                  <p>Heat a lightly oiled griddle or frying pan over medium high heat. Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.</p>
                  </div>
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
                <input type="hidden" name="dish" value="pancakes"/>
                  <input type="submit">
                  </form>
                  <?php } ?>
                    <br>
                    <div class="user-comments">
                      <?php
                        $filename = __DIR__ . '/pancake-comments.txt';
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
