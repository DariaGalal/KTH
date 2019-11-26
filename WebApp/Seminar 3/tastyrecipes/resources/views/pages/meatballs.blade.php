<link rel="stylesheet" href="{{asset('css/recipe.css')}}">
@extends('layouts.menu')

@section('content')
<div class="slider">
  <img class="food-picture" src="{!!asset('images/meatballs.jpg')!!}">
</div>
<div class="food-info">
  <h2 class="food-name">Meatballs</h2>
  <div class="ingredients">
    <h3 class="title">Ingredients</h3>
    <li>1 pound ground beef</li>
    <li>1/2 pound ground veal</li>
    <li>1/2 pound ground pork</li>
    <li>2 cloves of garlic, minced</li>
    <li>2 eggs</li>
    <li>1 cup freshly grated Romano cheese</li>
    <li>1 1/2 tablesppons chopped Italian flat leaf parsley</li>
    <li>Salt and pepper to taste</li>
    <li>2 cups stale Italian bread, crumbled</li>
    <li>1 1/2 cups lukewarm water</li>
    <li>1 cup olive oil</li>
  </div>
  <div class="method">
    <h3 class="title">Method</h3>
    <p>Combine beef, veal, and pork in a large bowl. Add garlic, eggs, cheese, parsley, salt and pepper.
      Blend bread crumbs into meat mixture. Slowly add the water 1/2 cup at a time. The mixture should be very moist but still hold its shape if rolled into meatballs.
                (I usually use about 1 1/4 cups of water). Shape into meatballs.
                Heat olive oil in a large skillet. Fry meatballs in batches. When the meatball is very brown and slightly crisp remove from the heat and drain on a paper towel.
                (If your mixture is too wet, cover the meatballs while they are cooking so that they hold their shape better).
    </p>
  </div>
  <div class="comments">
    <h3 class="title">Comments</h3>
    @if(isset(Auth::user()->email))
    <form class="" action="/post" method="post">
    <textarea placeholder="Enter comment"></textarea>
    <button type="submit" name="comment" value="comment">Send</button>
        </form>
    @endif
    {{PostController::show()}}
  </div>
</div>

@endsection
