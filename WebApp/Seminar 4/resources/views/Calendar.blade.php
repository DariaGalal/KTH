@extends('layout.base')
<link rel="stylesheet" href="{{asset('css/calendar.css')}}">
@section('content')
<div class="title m-b-md">
    Calendar
</div>
<div class="links">
  <a href="{{ url('Calendar') }}">Calendar</a>
  <a href="{{ url('posts/1') }}">Meatballs</a>
  <a href="{{ url('posts/2') }}">Pancakes</a>
  <a href="{{ url('/') }}">Home</a>
</div>

<div class="slider">
  <p class="calendar-head"> </p>
  <div class="week-days">
    <p class="week-days">Monday Tuesday Wednesday Thursday Friday Saturday Sunday</p>
    <div class="grid-container">
      <div class="grid-item pancake">1
        <a href="{{ url('posts/2') }}" class="day"></a>
      </div>
      <div class="grid-item">2</div>
      <div class="grid-item">3</div>
      <div class="grid-item meatballs">4
        <a href="{{ url('posts/1') }}" class="day"></a>
      </div>
      <div class="grid-item">5</div>
      <div class="grid-item">6</div>
      <div class="grid-item">7</div>
      <div class="grid-item">8</div>
      <div class="grid-item">9</div>
      <div class="grid-item">10</div>
      <div class="grid-item">11</div>
      <div class="grid-item">12</div>
      <div class="grid-item">13</div>
      <div class="grid-item">14</div>
      <div class="grid-item">15</div>
      <div class="grid-item">16</div>
      <div class="grid-item">17</div>
      <div class="grid-item">18</div>
      <div class="grid-item">19</div>
      <div class="grid-item">20</div>
      <div class="grid-item">21</div>
      <div class="grid-item">22</div>
      <div class="grid-item">23</div>
      <div class="grid-item">24</div>
      <div class="grid-item">25</div>
      <div class="grid-item">26</div>
      <div class="grid-item">27</div>
      <div class="grid-item">28</div>
    </div>
  </div>
</div>

@endsection
