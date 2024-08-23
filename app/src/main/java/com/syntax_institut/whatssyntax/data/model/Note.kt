package com.syntax_institut.whatssyntax.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val text: String,
    val contactName: String
)
