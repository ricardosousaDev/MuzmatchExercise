package com.ricardo.muzmatchexercise.ui.viewmodel

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ricardo.muzmatchexercise.data.local.ChatEntity
import com.ricardo.muzmatchexercise.data.local.ContactEntity
import com.ricardo.muzmatchexercise.data.repository.ChatRepository
import com.ricardo.muzmatchexercise.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val contactRepository: ContactRepository
) : BaseViewModel() {

    private val mutableChatState: MutableStateFlow<List<ChatEntity>> =
        MutableStateFlow(listOf())
    val chatState: Flow<List<ChatEntity>> = mutableChatState

    private val mutableContactData: MutableLiveData<ContactEntity> =
        MutableLiveData()
    val contactData: LiveData<ContactEntity> = mutableContactData

    fun getChatWithUserId(userId: Int) {
        viewModelScope.launch {
            mutableUiState.value = LoadingState.Loading
            chatRepository.getChatWithUserId(userId).collect {
                mutableChatState.value = it
                mutableUiState.value = LoadingState.Finished
            }
        }
    }

    fun getContactByUserId(userId: Int) {
        viewModelScope.launch {
            contactRepository.getContactByUserId(userId).collect {
                mutableContactData.value = it
            }
        }
    }

    fun addChatMessage(userId: Int, isReply: Boolean, message: String) {
        val timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).epochSecond

        viewModelScope.launch {
            chatRepository.addChatMessage(
                ChatEntity(
                    fromUserId = userId, isReply = isReply, message = message,
                    timestamp = timestamp
                )
            )
        }
    }

    fun addRandomReceivedMessage(userId: Int) {
        var randomText = ""
        LoremIpsum((10..30).random()).values.iterator().forEach {
            randomText += it
        }
        addChatMessage(userId, false, randomText)
    }
}