package com.application.ui.messages.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.data.model.Message
import com.application.data.model.User
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.databaseusecases.SendMessageToUserUseCase
import com.application.extensions.delegate
import com.application.ui.base.BaseViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ChatViewModel @AssistedInject constructor(
    @Assisted private val stateHandle: SavedStateHandle,
    private val sendMessageToUserUseCase: SendMessageToUserUseCase
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<ChatViewModel>

    private val _message = MutableLiveData<Message>()
    val messages : LiveData<Message> = _message

    init {
        listenForMessages()
    }

    var recipient: User by stateHandle.delegate(User())

    fun setNewRecipient(user: User) {
        recipient = user
    }

    fun sendMessage(message: String) {
        sendMessageToUserUseCase.execute(
            params = SendMessageToUserUseCase.Params(recipient.uid, message),
            stateReducer = {}
        )
    }

    private fun listenForMessages() {
        val ref = FirebaseDatabase.getInstance().getReference("/messages")

        ref.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}
            override fun onChildChanged(p0: DataSnapshot, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot) {}
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(Message::class.java) ?: return
                _message.value = chatMessage
            }
        })
    }
}