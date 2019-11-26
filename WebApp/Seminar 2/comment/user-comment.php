<?php
require_once 'comment.php';
session_start();
if($_POST['dish'] == "meatballs"){
$filename = __DIR__ . '/../HTML/meatball-comments.txt';
if (!empty($_POST['msg'])) {
    $entry = new Comment($_SESSION['user'], $_POST['msg']);
    $entry->setDish("meatballs");
    file_put_contents($filename, serialize($entry) . ";\n", FILE_APPEND);
    header('Location: ../HTML/meatballs.php');
    }
  }
  elseif ($_POST['dish'] == "pancakes") {
    $filename = __DIR__ . '/../HTML/pancake-comments.txt';
    if (!empty($_POST['msg'])) {
        $entry = new Comment($_SESSION['user'], $_POST['msg']);
        $entry->setDish("pancakes");
        file_put_contents($filename, serialize($entry) . ";\n", FILE_APPEND);
        header('Location: ../HTML/pancakes.php');
        }
  }
?>
