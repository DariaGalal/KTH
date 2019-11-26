@extends('layout.base')

@section('content')
<div class="title m-b-md">
    Tasty Recipes
</div>
@if (Auth::check())
  <p>Weclome {{Auth::user()->name}}! Explore our meals in the calendar or click on one right below!</p>
@else
  <p>Weclome! Explore our meals in the calendar or click on one right below!</p>
@endif
<br>
<div class="links">
  <a href="{{ url('Calendar') }}">Calendar</a>
  <a href="{{ url('posts/1') }}">Meatballs</a>
  <a href="{{ url('posts/2') }}">Pancakes</a>
  <a href="{{ url('/') }}">Home</a>
</div>
@endsection
