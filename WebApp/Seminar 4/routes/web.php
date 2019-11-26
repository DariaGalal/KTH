<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/Calendar', 'PagesController@getCalendar');

Route::get('/Meatballs', 'PagesController@getMeatballs');

Route::get('/Pancakes', 'PagesController@getPancakes');

Route::get('/', 'PagesController@getIndex');

Route::resource('posts', 'PostController');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Route::post('comments/{post_id}', ['uses' => 'CommentsController@store', 'as' => 'comments.store']);

Route::delete('comments/{id}', ['uses' => 'CommentsController@destroy', 'as' => 'comments.destroy']);

Route::get('comment/{id}/delete', ['uses' => 'CommentsController@delete', 'as' => 'comments.delete']);
