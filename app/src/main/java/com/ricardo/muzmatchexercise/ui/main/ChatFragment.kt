package com.ricardo.muzmatchexercise.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ricardo.muzmatchexercise.R
import com.ricardo.muzmatchexercise.di.ViewModelFactory
import com.ricardo.muzmatchexercise.ui.viewmodel.ChatViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChatFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var chatViewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatViewModel =
            ViewModelProvider(this, viewModelFactory).get(ChatViewModel::class.java)

        return inflater.inflate(R.layout.chat_fragment, container, false)
    }
}