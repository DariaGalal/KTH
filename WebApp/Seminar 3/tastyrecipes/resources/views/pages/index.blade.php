<link rel="stylesheet" href="{{asset('css/homepage.css')}}">
@extends('layouts.menu')

@section('content')
<div class="slider">
</div>
<div class="information">
  @if(isset(Auth::user()->email))
  <p>Welcome {{Auth::user()->email}}! Feel free to check out the recipes or why not the calendar?</p>
  @else
  <p>Welcome! Feel free to check out the calendar or directly to our recipes!</p>
  @endif
</div>
@endsection
