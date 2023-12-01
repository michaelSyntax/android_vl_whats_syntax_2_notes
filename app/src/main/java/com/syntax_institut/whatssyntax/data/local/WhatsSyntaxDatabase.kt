package com.syntax_institut.whatssyntax.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syntax_institut.whatssyntax.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class WhatsSyntaxDatabase : RoomDatabase() {
    abstract val dao: WhatsSyntaxDatabaseDao
}

private lateinit var INSTANCE: WhatsSyntaxDatabase

fun getDatabase(context: Context): WhatsSyntaxDatabase {

    synchronized(WhatsSyntaxDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                WhatsSyntaxDatabase::class.java,
                "ws_database"
            ).build()
        }
        return INSTANCE
    }

}
