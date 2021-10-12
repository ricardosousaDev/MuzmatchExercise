package com.ricardo.muzmatchexercise.data.repository

import com.ricardo.muzmatchexercise.data.local.ChatDao
import com.ricardo.muzmatchexercise.data.local.ChatEntity
import com.ricardo.muzmatchexercise.data.local.ContactEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val chatDao: ChatDao
) {
    fun getChatWithUserId(userId: Int): Flow<List<ChatEntity>> {
        return chatDao.getAllChatMessagesFrom(userId)
    }

    suspend fun addChatMessage(chatMessage: ChatEntity) = withContext(Dispatchers.IO) {
        async {
            chatDao.addChatMessage(chatMessage)
        }
    }.await()
}