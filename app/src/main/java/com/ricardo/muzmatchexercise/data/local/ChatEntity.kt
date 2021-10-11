package com.ricardo.muzmatchexercise.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ricardo.muzmatchexercise.util.getMessageDay
import com.ricardo.muzmatchexercise.util.getMessageTime

@Entity(tableName = "Chat")
data class ChatEntity constructor(
    @PrimaryKey
    val id: Int = 0,
    val fromUserId: Int = 0,
    val message: String,
    val timestamp: Long
) {
    val messageDay
        get() = timestamp.getMessageDay()
    val messageTime
        get() = timestamp.getMessageTime()
}