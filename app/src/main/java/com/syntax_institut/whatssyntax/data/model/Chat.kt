package com.syntax_institut.whatssyntax.data.model

data class Chat(
    val id: Int,
    val contact: Contact,
    val lastMessage: Message
)