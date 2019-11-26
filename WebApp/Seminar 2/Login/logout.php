<?php
session_start();
  session_destroy();
   echo 'You are now being logged out';
   header('Refresh: 2; URL = ../html/index.php');
?>
