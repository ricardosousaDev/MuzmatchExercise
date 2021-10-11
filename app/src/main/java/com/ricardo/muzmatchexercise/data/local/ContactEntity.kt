package com.ricardo.muzmatchexercise.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class ContactEntity constructor(
    @PrimaryKey
    val userId: Int = 0,
    val name: String,
    val profilePicUrl: String
)