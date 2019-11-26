@extends('layout.base')
{!!Html::style('css/parsley.css')!!}
{!!Html::script('js\jquery-3.3.1.min.js')!!}
{!!Html::script('js\parsley.min.js')!!}
@section('content')
<div class="pic"></div>

<div class="title m-b-md">
    Meatballs
</div>
<div class="links">
  <a href="{{ url('Calendar') }}">Calendar</a>
  <a href="{{ url('posts/1') }}">Meatballs</a>
  <a href="{{ url('posts/2') }}">Pancakes</a>
  <a href="{{ url('/') }}">Home</a>
</div>

  <img class="pic" src="{{asset('img/meatballs.jpg')}}" alt="Meatballs">
</div>
<div class="food">
  <h1>Ingredients</h1>
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
  <br>
  <h1>Method</h1>
  <li>Combine beef, veal, and pork in a large bowl. Add garlic, eggs, cheese, parsley, salt and pepper.</li>
  <li>Blend bread crumbs into meat mixture. Slowly add the water 1/2 cup at a time.</li>
  <li>The mixture should be very moist but still hold its shape if rolled into meatballs.</li>
  <li>(I usually use about 1 1/4 cups of water). Shape into meatballs.</li>
  <li>Heat olive oil in a large skillet. Fry meatballs in batches.</li>
  <li>When the meatball is very brown and slightly crisp remove from the heat and drain on a paper towel.</li>
  <li>(If your mixture is too wet, cover the meatballs while they are cooking so that they hold their shape better).</li>
</div>
<div class="comment">
  <br>
  <div class="comment-cont">
    <form id="com">
  <br>
      <div class="row">
          <div class="col-md-6">
          @if (Auth::check())
            {{Form::textarea('comment', null, array('required', 'validate' => ''))}}
            <br>
            {{Form::submit('Add Comment')}}
          @endif
          </div>
      </div>
    </form>
    <div class="posting">
        <div class="post">
          <h1>{{$post->title}}</h1>
          <p>{{$post->body}}</p>
        </div>
        <div class="col-md-6 col-md-offset-2">
          @foreach($post->comments as $comment)
            {{$comment->name}}
            <br>
            {{$comment->comment}}
            @if (Auth::check() && Auth::User()->name == $comment->name)
              {{Form::open(['route' => ['comments.destroy', $comment->id], 'method' => 'DELETE'])}}
              {{Form::submit('Delete')}}
              {{Form::close()}}
            @endif
            <br>
            <br>
            <br>
          @endforeach
        </div>
    </div>
  </div>
  <script>
      var CSRF_TOKEN = $('meta[name="csrf-token"]').attr('content');

      /* COMMENT - POST. */
      $('#com').on('submit', function (e) {
        var cmt = $('textarea[name="comment"]').val();
        console.log(cmt);
        //ajax call here
        $.ajax({
          headers: {
            'X-CSRF-TOKEN': CSRF_TOKEN
          },
          type : 'POST',
          url : 'http://localhost:8000/comments/1',
          data: {
            comment: cmt
          },
          success : function(d) {
            location.reload();
          },
          error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown);
            console.log(textStatus);
            console.log(XMLHttpRequest);
          }
        });
        e.preventDefault();
      });
    </script>
@endsection
