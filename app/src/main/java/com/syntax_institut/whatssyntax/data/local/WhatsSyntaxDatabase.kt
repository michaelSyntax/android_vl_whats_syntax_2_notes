package com.syntax_institut.whatssyntax.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syntax_institut.whatssyntax.data.model.Note

@Database(entities = [Note::class], version = 2)
abstract class WhatsSyntaxDatabase: RoomDatabase() {

    abstract val dao: WhatsSyntaxDAO

    companion object {
        private lateinit var instance: WhatsSyntaxDatabase

        //private var instance: WhatsSyntaxDatabase? = null

        fun getDatabase(context: Context) : WhatsSyntaxDatabase {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WhatsSyntaxDatabase::class.java,
                        "whats-syntax-database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }
}