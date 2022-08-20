package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myposts.databinding.ActivityCommentBinding
import com.example.myposts.databinding.CommentListItemBinding

class CommentRVAdapter( var commentList: List<Comment>):RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var binding = CommentListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
       var currentComment = commentList.get(position)

        with(holder.binding){
            tvPostId.text = currentComment.postId.toString()
            tvid.text = currentComment.id.toString()
            tvName.text = currentComment.name
            tvEmail.text=currentComment.email
            tvBodyy.text = currentComment.body
        }
    }

    override fun getItemCount(): Int {
       return commentList.size
    }
}
class CommentViewHolder(var binding: CommentListItemBinding): RecyclerView.ViewHolder(binding.root)