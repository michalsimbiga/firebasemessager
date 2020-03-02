package com.application.di

import com.application.di.module.uimodules.*
import com.application.ui.auth.login.LoginFragment
import com.application.ui.MainActivity
import com.application.ui.messages.MessagesFragment
import com.application.ui.auth.register.RegisterFragment
import com.application.ui.messages.chat.ChatFragment
import com.application.ui.messages.newMessage.NewMessageFragment
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