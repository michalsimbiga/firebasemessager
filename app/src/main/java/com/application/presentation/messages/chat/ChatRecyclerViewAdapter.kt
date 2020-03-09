package com.application.presentation.messages.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.data.model.Message
import com.application.databinding.FragmentChatRecyclerFromItemBinding
import com.application.databinding.FragmentChatRecyclerMyItemBinding
import com.application.data.model.User
import com.application.domain.extensions.autoNotify
import com.application.presentation.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlin.properties.Delegates

class ChatRecyclerViewAdapter : AbstractChatRecyclerViewAdapter() {

    private var chatMessages: List<MessageEntry> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { old, new -> old.message.id == new.message.id }
    }

    private var receipient: User? = null
    private var currentUser: User? = null

    fun setRecipient(user: User) = run { receipient = user }
    fun setCurrentUser(user: User) = run { currentUser = user }

    fun addMessage(message: Message) = run {
        var newMessage: MessageEntry? = null
        if (message.fromId == currentUser?.uid && message.toId == receipient?.uid)
            newMessage = MessageEntry.MessageTo(message)
        else if (message.fromId == receipient?.uid && message.toId == currentUser?.uid)
            newMessage = MessageEntry.MessageFrom(message)
        newMessage?.let { chatMessages = chatMessages + it }
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun getItemViewType(position: Int): Int {
        return when (chatMessages[position]) {
            is MessageEntry.MessageTo -> RecyclerViewType.MessageTo.id
            is MessageEntry.MessageFrom -> RecyclerViewType.MessageFrom.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (RecyclerViewType.fromId(viewType)) {
            RecyclerViewType.MessageTo ->
                ChatMyViewHolder(
                    FragmentChatRecyclerMyItemBinding.inflate(layoutInflater, parent, false)
                )
            RecyclerViewType.MessageFrom ->
                ChatFromViewHolder(
                    FragmentChatRecyclerFromItemBinding.inflate(layoutInflater, parent, false)
                )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ChatMyViewHolder -> holder.bind(Pair(currentUser, chatMessages[position]))
            is ChatFromViewHolder -> holder.bind(Pair(receipient, chatMessages[position]))
        }
    }
}

enum class RecyclerViewType(val id: Int) {
    MessageFrom(0),
    MessageTo(1);

    companion object {
        fun fromId(id: Int): RecyclerViewType = when (id) {
            MessageFrom.id -> MessageFrom
            MessageTo.id -> MessageTo
            else -> throw error("Id not present")
        }
    }
}

sealed class MessageEntry(var message: Message) {
    data class MessageFrom(val newMessage: Message) : MessageEntry(newMessage)
    data class MessageTo(val newMessage: Message) : MessageEntry(newMessage)
}

class ChatFromViewHolder(private val binding: FragmentChatRecyclerFromItemBinding) :
    BaseViewHolder<Pair<User?, MessageEntry?>>(binding.root) {

    override fun bind(item: Pair<User?, MessageEntry?>?) = with(binding) {
        Glide
            .with(root.context)
            .load(item?.first?.profileImage)
            .centerCrop()
            .into(fragmentChatFromPicture)

        binding.fragmentChatFromText.text = item?.second?.message?.text
    }
}

class ChatMyViewHolder(private val binding: FragmentChatRecyclerMyItemBinding) :
    BaseViewHolder<Pair<User?, MessageEntry?>>(binding.root) {

    override fun bind(item: Pair<User?, MessageEntry?>?) = with(binding) {
        Glide
            .with(root.context)
            .load(item?.first?.profileImage)
            .centerCrop()
            .into(fragmentChatFromPicture)

        binding.fragmentChatFromText.text = item?.second?.message?.text
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
