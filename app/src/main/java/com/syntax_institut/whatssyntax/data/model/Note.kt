package com.syntax_institut.whatssyntax.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val text: String,
    val contactName: String
)