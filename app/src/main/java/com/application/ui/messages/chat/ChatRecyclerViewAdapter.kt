package com.application.ui.messages.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.data.model.Message
import com.application.databinding.FragmentChatRecyclerFromItemBinding
import com.application.databinding.FragmentChatRecyclerMyItemBinding
import com.application.data.model.User
import com.bumptech.glide.Glide

class ChatRecyclerViewAdapter : RecyclerView.Adapter<ChatFromViewHolder>() {

    private val chatMessages = mutableListOf<Message>()

    private var receipient: User? = null

    fun setRecipient(user: User) {
        receipient = user
    }

    fun addMessages(list: List<Message>) {}

    fun addMessage(message: Message) {
        chatMessages.add(message)
        notifyItemInserted(chatMessages.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatFromViewHolder {
        val binding = FragmentChatRecyclerFromItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ChatFromViewHolder(binding)
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun onBindViewHolder(holder: ChatFromViewHolder, position: Int) {
        holder.bind(receipient, chatMessages[position])
    }
}

class ChatFromViewHolder(private val binding: FragmentChatRecyclerFromItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recipient: User?, message: Message) = with(binding) {
        Glide.with(root.context)
            .load(recipient?.profileImage)
            .centerCrop()
            .into(fragmentChatFromPicture)

        binding.fragmentChatFromText.text = message.text
    }
}

class ChatMyViewHolder(private val binding: FragmentChatRecyclerMyItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, message: String) = with(binding) {
        Glide.with(root.context)
            .load(user.profileImage)
            .centerCrop()
            .into(fragmentChatFromPicture)

        binding.fragmentChatFromText.text = message
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
