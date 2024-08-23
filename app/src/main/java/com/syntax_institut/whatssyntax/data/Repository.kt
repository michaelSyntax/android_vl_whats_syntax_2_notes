package com.syntax_institut.whatssyntax.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syntax_institut.whatssyntax.BuildConfig
import com.syntax_institut.whatssyntax.data.local.WhatsSyntaxDatabase
import com.syntax_institut.whatssyntax.data.model.Call
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.data.model.Note
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApi

class Repository(private val api: WhatsSyntaxApi, private val db: WhatsSyntaxDatabase) {

    private val number = 1

    // Erwartet im File "local.properties" einen Eintrag: apiKey="meinApiKey"
    private val key = BuildConfig.apiKey
    private val tag = "REPOSITORY"

    val notes = db.dao.getNotes()

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
        try {
            val result = api.retrofitService.getChats(number, key)
            _chats.postValue(result)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    suspend fun getCalls() {
        try {
            val result = api.retrofitService.getCalls(number, key)
            _calls.postValue(result)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    suspend fun getMessagesByChatId(chatId: Int) {
        try {
            _messages.postValue(listOf())
            val result = api.retrofitService.getMessagesByChatId(number, chatId, key)
            _messages.postValue(result)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    suspend fun sendNewMessage(chatId: Int, message: Message) {
        try {
            api.retrofitService.sendNewMessage(number, key, chatId, message)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    suspend fun getContacts() {
        try {
            val result = api.retrofitService.getContacts(number, key)
            _contacts.postValue(result)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    suspend fun getProfile() {
        try {
            val result = api.retrofitService.getProfile(number, key)
            _profile.postValue(result)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    suspend fun setProfile(profile: Profile) {
        try {
            api.retrofitService.setProfile(number, profile, key)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    suspend fun insertNote(note: Note) {
        db.dao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        db.dao.delete(note)
    }

    suspend fun deleteNoteById(id: Long) {
        db.dao.deleteNoteById(id)
    }
}