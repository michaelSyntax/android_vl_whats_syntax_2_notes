package com.syntax_institut.whatssyntax.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val id: Int,
    val name: String,
    val number: String,
    val image: String,
    val status: Status?
)