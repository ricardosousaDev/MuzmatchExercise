package com.ricardo.muzmatchexercise.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ricardo.muzmatchexercise.data.repository.ContactRepository
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {
}