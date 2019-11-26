<?php

/********
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*********/

//Route::get('/homepage', function(){
//  return view('pages.homepage');
//});

Route::get('/', 'PageController@index');
Route::get('/meatballs', 'PageController@meatballs');
Route::get('/pancakes', 'PageController@pancakes');
Route::get('/calendar', 'PageController@calendar');

Route::get('/login', 'UserController@index');
Route::get('/main', 'UserController@index');
Route::post('main/checklogin', 'UserController@checklogin');
Route::get('main/successlogin', 'UserController@successlogin');
Route::get('main/logout', 'UserController@logout');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Route::get('/posts/{post}', 'CommentController@post');
Route::post('/comments', 'CommentController@show');
