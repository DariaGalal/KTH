<link rel="stylesheet" href="{{asset('css/recipe.css')}}">
@extends('layouts.menu')

@section('content')
<div class="slider">
  <img class="food-picture" src="{!!asset('images/pancakes.jpg')!!}">
</div>
<div class="food-info">
  <h2 class="food-name">Pancakes</h2>
  <div class="ingredients">
    <h3 class="title">Ingredients</h3>
    <li>1 1/2 cups all-purpose flour</li>
    <li>3 1/2 teaspoon baking powder</li>
    <li>1 teaspoon salt</li>
    <li>1 tablespoon white sugar</li>
    <li>1 1/4 cups milk</li>
    <li>1 egg</li>
    <li>3 tablespoons butter, melted</li>
    <li>1 teaspoon vanilla</li>
  </div>
  <div class="method">
    <h3 class="title">Method</h3>
    <p>In a large bowl, sift together the flour, baking powder, salt and sugar. Make a well in the center and pour in the milk, egg, melted butter and vanilla; mix until smooth.
        Heat a lightly oiled griddle or frying pan over medium high heat. Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.
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
