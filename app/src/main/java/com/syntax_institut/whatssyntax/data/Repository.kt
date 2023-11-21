package com.syntax_institut.whatssyntax.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syntax_institut.whatssyntax.data.model.Call
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApi
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApiService
import kotlinx.coroutines.delay

class Repository(private val api: WhatsSyntaxApi) {

    private val number = 2
    private val key = "test"

    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>>
        get() = _chats

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>>
        get() = _messages

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>>
        get() = _contacts

    private val _calls = MutableLiveData<List<Call>>()
    val calls: LiveData<List<Call>>
        get() = _calls

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile>
        get() = _profile

    suspend fun getChats() {
        val result = api.retrofitService.getChats(number, key)
        if (result != null) {
            _chats.postValue(result!!)
        }
    }

    suspend fun getCalls() {
        val result = api.retrofitService.getCalls(number, key)
        _calls.postValue(result)
    }

    suspend fun getMessagesByChatId(chatId: Int) {
        _messages.postValue(listOf())
        val result = api.retrofitService.getMessagesByChatId(number, chatId, key)
        _messages.postValue(result)
    }

    suspend fun sendNewMessage(chatId: Int, message: Message) {
        api.retrofitService.sendNewMessage(number, chatId, message, key)
    }

    suspend fun getContacts() {
        val result = api.retrofitService.getContacts(number, key)
        _contacts.postValue(result)
    }

    suspend fun getProfile() {
        val result = api.retrofitService.getProfile(number, key)
        _profile.postValue(result)
    }

    suspend fun setProfile(profile: Profile) {
        api.retrofitService.setProfile(number, profile, key)
    }

}