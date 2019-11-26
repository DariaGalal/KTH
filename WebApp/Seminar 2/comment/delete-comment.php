<?php
require_once 'comment.php';

session_start();
if (empty($_GET['timestamp'])) {
  echo "An error occured, please try again.";
}
if (!empty($_GET['timestamp'] && $_GET['dish'] == "meatballs")) {
  $filename = __DIR__ . '/../HTML/meatball-comments.txt';
    $entries = explode(";\n", file_get_contents($filename));
    for ($i = count($entries) - 1; $i >= 0; $i--) {
        $entry = unserialize($entries[$i]);
        if ($entry instanceof comment && $entry->getTimestamp() == $_GET['timestamp']) {
            echo "inside";
            $entry->setDeleted(true);
            $entries[$i] = serialize($entry);
            break;
        }
    }
    file_put_contents($filename, implode(";\n", $entries));
    header('Location: ../HTML/meatballs.php');
}
elseif (!empty($_GET['timestamp'] && $_GET['dish'] == "pancakes")) {
  $filename = __DIR__ . '/../HTML/pancake-comments.txt';
    $entries = explode(";\n", file_get_contents($filename));
    for ($i = count($entries) - 1; $i >= 0; $i--) {
        $entry = unserialize($entries[$i]);
        if ($entry instanceof comment && $entry->getTimestamp() == $_GET['timestamp']) {
            echo "inside";
            $entry->setDeleted(true);
            $entries[$i] = serialize($entry);
            break;
        }
    }
    file_put_contents($filename, implode(";\n", $entries));
    header('Location: ../HTML/pancakes.php');
}

?>
