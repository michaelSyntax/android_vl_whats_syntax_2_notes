package com.syntax_institut.whatssyntax.data.model

import androidx.room.Entity
import com.syntax_institut.whatssyntax.data.remote.BASE_URL

@Entity
data class Contact(
    val id: Int,
    val name: String,
    val number: String,
    val image: String,
    val status: Status?
) {
    fun getImageURL(): String {
        return BASE_URL + image
    }
}