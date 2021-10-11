package com.ricardo.muzmatchexercise.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM Chat")
    fun getAllChatMessages(): Flow<List<ChatEntity>>
}