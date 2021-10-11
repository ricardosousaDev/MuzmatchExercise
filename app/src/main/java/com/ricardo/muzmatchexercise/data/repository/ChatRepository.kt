package com.ricardo.muzmatchexercise.data.repository

import com.ricardo.muzmatchexercise.data.local.ChatDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val chatDao: ChatDao
) {

}