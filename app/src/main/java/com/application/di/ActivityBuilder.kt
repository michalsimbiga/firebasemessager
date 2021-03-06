package com.application.di

import com.application.di.module.uimodules.*
import com.application.presentation.auth.login.LoginFragment
import com.application.presentation.main.MainActivity
import com.application.presentation.messages.messages.MessagesFragment
import com.application.presentation.auth.register.RegisterFragment
import com.application.presentation.messages.chat.ChatFragment
import com.application.presentation.messages.newMessage.NewMessageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector(modules = [MessagesModule::class])
    abstract fun bindMessagesFragment(): MessagesFragment

    @ContributesAndroidInjector(modules = [NewMessageModule::class])
    abstract fun bindNewMessageFragment(): NewMessageFragment

    @ContributesAndroidInjector(modules = [ChatModule::class])
    abstract fun bindChatFragment(): ChatFragment
}