<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Validator;
use Auth;
use AuthenticatesUsers;

class UserController extends Controller
{
    function index(){
      return view('pages.login');
    }

    function checkLogin(Request $request)
    {
      $this->validate($request, [
        'email' => 'required|email',
        'password' => 'required|alphaNum|min:3'
      ]);

      $user_data = array(
        'email' => $request->get('email'),
        'password' => $request->get('password')
      );

      if(Auth::attempt($user_data)){
        return redirect('main/successlogin');
      }
      else{
        return back()->with('error', 'Wrong Login Details');
      }
    }

    function successlogin(){
      return view('pages.index');
    }

    function logout(){
      Auth::logout();
      return view('pages.index');
    }
}
