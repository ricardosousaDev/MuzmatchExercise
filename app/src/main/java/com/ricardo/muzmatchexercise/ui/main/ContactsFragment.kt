package com.ricardo.muzmatchexercise.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ricardo.muzmatchexercise.R
import com.ricardo.muzmatchexercise.di.ViewModelFactory
import com.ricardo.muzmatchexercise.ui.viewmodel.ContactViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContactsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactViewModel = ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)

        return inflater.inflate(R.layout.contacts_fragment, container, false)
    }
}