package com.syntax_institut.whatssyntax.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.syntax_institut.whatssyntax.data.model.Note

@Dao
interface WhatsSyntaxDatabaseDao {

    @Query("SELECT * FROM Note ORDER BY id DESC")
    fun getNotes() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}