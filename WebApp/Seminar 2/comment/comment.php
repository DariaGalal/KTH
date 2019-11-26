<?php

class Comment {

    private $user_name;
    private $msg;
    private $timestamp;
    private $deleted;
    private $dish;

    public function __construct($user_name, $msg) {
        $this->user_name = $user_name;
        $this->msg = $msg;
        $this->timestamp = time();
        $this->deleted = false;
        $this->dish = null;
    }

    public function getUserName() {
        return $this->user_name;
    }

    public function getMsg() {
        return $this->msg;
    }

    public function getTimestamp() {
        return $this->timestamp;
    }

    public function isDeleted() {
        return $this->deleted;
    }

    public function setDeleted($deleted) {
        $this->deleted = $deleted;
    }

    public function getDish(){
      return $this->dish;
    }

    public function setDish($dish){
      $this->dish = $dish;
    }

}
?>
