<link rel="stylesheet" href="{{asset('css/login.css')}}">
@extends('layouts.menu')
@section('content')
<div class="slider">
  <form action = "{{url('/main/checklogin')}}" method="post">
    {{ csrf_field() }}
    <div class="login-container">
      <label><b>Email</b></label>
      <input type="email" name="email" placeholder="Enter Email">
      <label><b>Password</b></label>
      <input type="password" name="password" placeholder="Enter Password" name="psw" required>
      <button type="submit" name="login" value="Login">Login</button>
    </div>
  </form>
</div>
@endsection
