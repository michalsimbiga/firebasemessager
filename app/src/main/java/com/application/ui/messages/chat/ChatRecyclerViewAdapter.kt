package com.application.ui.messages.chat

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.databinding.FragmentChatRecyclerFromItemBinding
import com.application.model.User
import com.bumptech.glide.Glide

class ChatRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class ChatFromViewHolder(private val binding: FragmentChatRecyclerFromItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) = with(binding) {
        Glide.with(root.context)
            .load(user.profileImage)
            .centerCrop()
            .into(fragmentChatFromPicture)
    }
}

//    class MyChatMessageViewHolder(private val binding: FragmentNewMessageRecyclerItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(user: User, onClickCallback: onUserItemClickListener?) =
//            with(binding) {
//
//                Glide
//                    .with(root.context)
//                    .load(user.profileImage)
//                    .centerCrop()
//                    .into(userItemImage)
//
//                userItemName.text = user.username
//                userItemMainLayout.setOnClickListener { onClickCallback?.invoke(user) }
//            }
