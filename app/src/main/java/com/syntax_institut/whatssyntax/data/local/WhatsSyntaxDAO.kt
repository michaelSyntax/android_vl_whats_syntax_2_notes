package com.syntax_institut.whatssyntax.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.syntax_institut.whatssyntax.data.model.Note

@Dao
interface WhatsSyntaxDAO {

    /*
    DatenBankDAO erstellen um folgende Funktionen bereitzustellen:
        Einzelne Note speichern
        Einzelne Note löschen
        Liste Notes aus DatenBank lesen
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE from notes_table where id = :id")
    suspend fun deleteNoteById(id: Long)

    @Query("SELECT * FROM notes_table")
    fun getNotes() : LiveData<List<Note>>

}