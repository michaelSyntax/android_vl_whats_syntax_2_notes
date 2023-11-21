package com.syntax_institut.whatssyntax

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.syntax_institut.whatssyntax.data.Repository
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApi
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = Repository(WhatsSyntaxApi)

    private var chatId = 0

    val chats = repository.chats
    val messages = repository.messages
    val contacts = repository.contacts
    val calls = repository.calls
    val profile = repository.profile

    fun getChats() {
        viewModelScope.launch {
            repository.getChats()
        }
    }

    fun getMessages(chatId: Int) {
        this.chatId = chatId
        viewModelScope.launch {
            repository.getMessagesByChatId(chatId)
        }
    }

    fun sendNewMessage(message: Message) {
        viewModelScope.launch {
            repository.sendNewMessage(chatId, message)
        }
    }

    fun getContacts() {
        viewModelScope.launch {
            repository.getContacts()
        }
    }

    fun getCalls() {
        viewModelScope.launch {
            repository.getCalls()
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            repository.getProfile()
        }
    }

    fun setProfile(profile: Profile) {
        viewModelScope.launch {
            repository.setProfile(profile)
        }
    }

}