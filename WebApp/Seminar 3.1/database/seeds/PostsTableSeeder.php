<?php

use Illuminate\Database\Seeder;

class PostsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run($request)
    {
      Post::create([
          $request->'nick_name'    => $request->'nick_name',
          $request->'msg'    => $request->'msg',
      ]);
    }
}
