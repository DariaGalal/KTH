<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Post;

class CommentController extends Controller
{
  funtion post(Post $post){
      Post::findOrFail($id);
  }
  function viewComment(Post $post){
    return view('/comments');
  }
}
