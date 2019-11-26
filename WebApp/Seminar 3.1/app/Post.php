<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Post extends Model
{
  private $nick_name;
  private $msg;
  private $timestamp;
  private $deleted;

  public funtion __construct($nick, $msg){
    $this->nick_name = $nick_name;
    $this->msg = $msg;
    $this->timestamp = time();
    $this->deleted = false;
  }

  public function getNickName() {
        return $this->nick_name;
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

}

?>
