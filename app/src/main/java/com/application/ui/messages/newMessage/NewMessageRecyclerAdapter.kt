package com.application.ui.messages.newMessage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.databinding.FragmentNewMessageRecyclerItemBinding
import com.bumptech.glide.Glide

typealias onUserItemClickListener = (UserItem) -> Unit

class NewMessageRecyclerAdapter : RecyclerView.Adapter<NewMessageViewHolder>() {

    private val people: MutableList<UserItem> = mutableListOf()

    private var onUserItemClickListener: onUserItemClickListener? = null

    fun setOnUserItemClickListener(callback: onUserItemClickListener?){
        onUserItemClickListener = callback
    }

    override fun getItemCount() = people.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMessageViewHolder {
        val binding: FragmentNewMessageRecyclerItemBinding =
            FragmentNewMessageRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        return NewMessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewMessageViewHolder, position: Int) {
        holder.bind(people[position], onUserItemClickListener)
    }
}

class NewMessageViewHolder(private val binding: FragmentNewMessageRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(userItem: UserItem, onClickCallback: onUserItemClickListener?){
        Glide
            .with(binding.root)
            .load(userItem.photoUrl)
            .centerCrop()
            .into(binding.userItemImage)
    }
}

data class UserItem(
    val photoUrl: String,
    val name: String
)