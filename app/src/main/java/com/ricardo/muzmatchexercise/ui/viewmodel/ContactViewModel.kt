package com.ricardo.muzmatchexercise.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ricardo.muzmatchexercise.data.local.ContactEntity
import com.ricardo.muzmatchexercise.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : BaseViewModel() {
    private val mutableContactsState: MutableStateFlow<List<ContactEntity>> =
        MutableStateFlow(listOf())
    val contactsState: Flow<List<ContactEntity>> = mutableContactsState

    fun getAllContacts() {
        viewModelScope.launch {
            mutableUiState.value = LoadingState.Loading
            contactRepository.getAllContacts().collect {
                mutableContactsState.value = it
            }
            mutableUiState.value = LoadingState.Finished
        }
    }
}