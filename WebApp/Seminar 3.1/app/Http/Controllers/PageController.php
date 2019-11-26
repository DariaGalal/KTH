<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class PageController extends Controller
{
    public function index(){
      return view('pages.index');
    }

    public function meatballs(){
      return view('pages.meatballs');
    }

    public function pancakes(){
      return view('pages.pancakes');
    }


    public function calendar(){
      return view('pages.calendar');
    }

    // public function login(){
    //   return view('pages.login');
    // }

}
