package com.application.ui.messages.newMessage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.databinding.FragmentNewMessageRecyclerItemBinding
import com.application.model.User
import com.bumptech.glide.Glide

typealias onUserItemClickListener = (User) -> Unit

class NewMessageRecyclerAdapter : RecyclerView.Adapter<NewMessageViewHolder>() {

    private val people: MutableList<User> = mutableListOf()

    private var onUserItemClickListener: onUserItemClickListener? = null

    fun setOnUserItemClickListener(callback: onUserItemClickListener?) {
        onUserItemClickListener = callback
    }

    fun setNewData(list: List<User>) {
        people.clear()
        people.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = people.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMessageViewHolder {
        val binding = FragmentNewMessageRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return NewMessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewMessageViewHolder, position: Int) =
        holder.bind(people[position], onUserItemClickListener)
}

class NewMessageViewHolder(private val binding: FragmentNewMessageRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, onClickCallback: onUserItemClickListener?) =
        with(binding) {

            Glide
                .with(root.context)
                .load(user.profileImage)
                .centerCrop()
                .into(userItemImage)

            userItemName.text = user.username
            userItemMainLayout.setOnClickListener { onClickCallback?.invoke(user) }
        }
}