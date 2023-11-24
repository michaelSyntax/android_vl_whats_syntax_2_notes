package com.syntax_institut.whatssyntax

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syntax_institut.whatssyntax.data.Repository
import com.syntax_institut.whatssyntax.data.local.getDatabase
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.data.model.Note
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.data.model.Status
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApi
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(WhatsSyntaxApi, getDatabase(application))

    private lateinit var currentChat: Chat

    val chats = repository.chats
    val messages = repository.messages
    val contacts = repository.contacts
    val calls = repository.calls
    val profile = repository.profile
    val notes = repository.notes

    private var _currentStatus = MutableLiveData<Status>()
    val currentStatus: LiveData<Status>
        get() = _currentStatus

    fun getChats() {
        viewModelScope.launch {
            repository.getChats()
        }
    }

    fun getMessages(chat: Chat) {
        this.currentChat = chat
        viewModelScope.launch {
            repository.getMessagesByChatId(currentChat.id)
        }
    }

    fun sendNewMessage(message: Message) {
        viewModelScope.launch {
            repository.sendNewMessage(currentChat.id, message)
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

    fun saveNote(message: Message) {
        val newNote = Note(null, message.text, currentChat.contact.name)
        viewModelScope.launch {
            repository.saveNote(newNote)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun setStatus(status: Status) {
        _currentStatus.value = status
    }

}