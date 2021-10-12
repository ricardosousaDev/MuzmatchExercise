package com.ricardo.muzmatchexercise.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricardo.muzmatchexercise.data.local.ContactEntity
import com.ricardo.muzmatchexercise.data.repository.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : BaseViewModel() {

    fun addTestContacts() {
        viewModelScope.launch {
            mutableUiState.value = LoadingState.Loading
            contactRepository.addContacts(
                listOf(
                    ContactEntity(
                        1001,
                        "Oprah",
                        "https://tinyurl.com/rmtuy3r7"
                    ),
                    ContactEntity(
                        1002,
                        "Ricardo",
                        "https://tinyurl.com/3cuabee9"
                    ),
                    ContactEntity(1003,
                    "Doge",
                    "https://tinyurl.com/87dhych5")
                )
            )
            mutableUiState.value = LoadingState.Finished
        }
    }
}