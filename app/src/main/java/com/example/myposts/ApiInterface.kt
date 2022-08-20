package com.example.myposts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.sql.RowId

interface ApiInterface {
    @GET("/posts")
    fun getPosts():Call<List<Post>>

    @GET("/posts/{id}")
    fun getPostsById(@Path("id")postId: Int):Call<Post>

    @GET ("/posts/{id}/comments")
    fun getComments(@Path("id") commentId: Int):Call<List<Comment>>
}