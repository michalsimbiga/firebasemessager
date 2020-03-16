package com.application.presentation.messages.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.data.model.Message
import com.application.data.model.User
import com.application.data.model.toUser
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.databaseusecases.SendMessageToUserUseCase
import com.application.domain.extensions.delegate
import com.application.domain.extensions.liveData
import com.application.domain.net.MyResult
import com.application.domain.usecase.authusecases.GetCurrentUserUseCase
import com.application.presentation.base.BaseViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber

class ChatViewModel @AssistedInject constructor(
    @Assisted private val stateHandle: SavedStateHandle,
    private val sendMessageToUserUseCase: SendMessageToUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<ChatViewModel>

    var messages by stateHandle.liveData(initialValue = mutableListOf<Message>())

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> = _currentUser

    init {
        getCurrentUser()
        listenForMessages()
    }

    var recipient: User by stateHandle.delegate(User())

    fun setNewRecipient(user: User) {
        recipient = user
    }

    fun sendMessage(message: String) {
        if (message.isBlank()) return
        sendMessageToUserUseCase.execute(
            params = SendMessageToUserUseCase.Params(recipient.uid, message),
            stateReducer = {}
        )
    }

    private fun getCurrentUser() {
        getCurrentUserUseCase.execute(
            stateReducer = { user -> if (user is MyResult.Success) _currentUser.value = user.data }
        )
    }

    private fun addMessage(message: Message){
        val temp = messages.value
        messages = MutableLiveData(temp?.plus(message)?.toMutableList()!!)
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

                Timber.i("TESTING onChildAdded $chatMessage")
                addMessage(chatMessage)
            }
        })
    }
}