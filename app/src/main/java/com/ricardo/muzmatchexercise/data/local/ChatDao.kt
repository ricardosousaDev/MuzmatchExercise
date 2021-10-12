package com.ricardo.muzmatchexercise.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM Chat WHERE fromUserId == :userId")
    fun getAllChatMessagesFrom(userId: Int): Flow<List<ChatEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addChatMessage(chatMessage: ChatEntity)
}