package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myposts.databinding.ActivityCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {
    var postId =0
    var comment =0
    lateinit var binding: ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostById()
//        setupToolbar()
        fetchComments()
        viewComments()
    }

    fun obtainPostId(){
        postId = intent.extras?.getInt("POST_ID") ?: 0
    }

    fun viewComments() {
        var extras = intent
        postId = extras.getIntExtra("POST_ID", 0)
    }
    fun fetchPostById(){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPostsById(postId)
        request.enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    var post = response.body()
                    if (post!=null)
                    binding.tvName.text =post?.title
                    binding.tvBdy.text = post?.body
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
//    fun setupToolbar(){
//        setSupportActionBar(binding.too)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//    }
    fun fetchComments(){
        var apiClient =ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments(postId)

        request.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
              var comment = response.body()
              if (response.isSuccessful){
                  Log.d("TAG",comment.toString())
                  comment?.let { displayComment(it) }
              }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun displayComment(comment: List<Comment>){
        var adapter = CommentRVAdapter(comment)
        binding.rcvComment.layoutManager = LinearLayoutManager(this)
        binding.rcvComment.adapter = adapter
    }

}