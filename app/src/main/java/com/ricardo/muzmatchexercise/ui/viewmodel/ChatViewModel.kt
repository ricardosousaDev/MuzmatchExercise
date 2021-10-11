package com.ricardo.muzmatchexercise.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ricardo.muzmatchexercise.data.repository.ChatRepository
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
)  : ViewModel() {
}