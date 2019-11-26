<?php

namespace App\Http\Controllers;
use App\Post;
use Illuminate\Http\Request;

class PagesController extends Controller
{
    public function getIndex(){
      return view('welcome');
    }

    public function getCalendar(){
      return view('Calendar');
    }

    public function getMeatballs(){
      return view('meatballs');
    }

    public function getPancakes(){
      return view('Pancakes');
    }
}
