package com.ricardo.muzmatchexercise.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Chat")
data class ChatEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fromUserId: Int = 0,
    val isReply: Boolean,
    val message: String,
    val timestamp: Long
)