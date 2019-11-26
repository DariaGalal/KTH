@extends('layout.base')
{!!Html::style('css/parsley.css')!!}
{!!Html::script('js\jquery-3.3.1.min.js')!!}
{!!Html::script('js\parsley.min.js')!!}
@section('content')
<div class="pic"></div>

<div class="title m-b-md">
    Pancakes
</div>
<div class="links">
  <a href="{{ url('Calendar') }}">Calendar</a>
  <a href="{{ url('posts/1') }}">Meatballs</a>
  <a href="{{ url('posts/2') }}">Pancakes</a>
  <a href="{{ url('/') }}">Home</a>
</div>

  <img class="pic" src="{{asset('img/pancakes.jpg')}}" alt="Pancakes">
</div>
<div class="food">
  <h1>Ingredients</h1>
<li>1 1/2 cups all-purpose flour</li>
<li>3 1/2 teaspoon baking powder</li>
<li>1 teaspoon salt</li>
<li>1 tablespoon white sugar</li>
<li>1 1/4 cups milk</li>
<li>1 egg</li>
<li>3 tablespoons butter, melted</li>
<li>1 teaspoon vanilla</li>
<br>
<h1>Method</h1>
<li>In a large bowl, sift together the flour, baking powder, salt and sugar.</li>
<li>Make a well in the center and pour in the milk, egg, melted butter and vanilla.</li>
<li>mix until smooth.</li>
<li>Heat a lightly oiled griddle or frying pan over medium high heat.</li>
<li>Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake.</li>
<li>Brown on both sides and serve hot.</li>
</div>
<div class="comment">
  <br>
  <div class="comment-cont">
    <form id="yolo">
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
      $('#yolo').on('submit', function (e) {
        var cmt = $('textarea[name="comment"]').val();
        console.log(cmt);
        //ajax call here
        $.ajax({
          headers: {
            'X-CSRF-TOKEN': CSRF_TOKEN
          },
          type : 'POST',
          url : 'http://localhost:8000/comments/2',
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
