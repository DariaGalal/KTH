<!DOCTYPE html>
<html lang="{{ config('app.locale') }}">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="{{asset('css/reset.css')}}">
      <link rel="stylesheet" href="{{asset('css/base.css')}}">
      <title{{config('app.name', 'Tasty')}}</title>
   </head>
   <body>
     <div class="container">
       <div class="menu">
         <ul>
           <li><a href="/">Home</a></li>
           <li><a href="/calendar">Calendar</a></li>
           <li>Recipes <i class="fa fa-angle-down"></i>
             <ul>
               <li><a href="/meatballs">Meatballs</a></li>
               <li><a href="/pancakes">Pancakes</a></li>
             </ul>
           </li>
           @if(isset(Auth::user()->email))
           <li class="user"><a href="{{ url('/main/logout') }}">Logout</a></li>
           @else
           <li class="user"><a href="/login">Login</a></li>
           @endif
         </ul>
         @yield('content')
       </div>
     </div><link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600' rel='stylesheet' type='text/css'>
     <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    </body>
    </html>
