package com.ricardo.muzmatchexercise.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.ricardo.muzmatchexercise.R
import com.ricardo.muzmatchexercise.databinding.ChatFragmentBinding
import com.ricardo.muzmatchexercise.di.ViewModelFactory
import com.ricardo.muzmatchexercise.ui.adapter.ChatAdapter
import com.ricardo.muzmatchexercise.ui.viewmodel.ChatViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ChatFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val args: ChatFragmentArgs by navArgs()

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var binding: ChatFragmentBinding

    private val chatAdapter = ChatAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatFragmentBinding.inflate(inflater)

        chatViewModel =
            ViewModelProvider(this, viewModelFactory).get(ChatViewModel::class.java)

        chatViewModel.getContactByUserId(args.userId)
        chatViewModel.getChatWithUserId(args.userId)

        chatViewModel.contactData.observe(viewLifecycleOwner, { contact ->
            binding.contactEntity = contact
        })

        lifecycleScope.launchWhenStarted {
            chatViewModel.chatState.collect {
                chatAdapter.setItems(it)

                scrollToBottom()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chatToolbar.inflateMenu(R.menu.chat_menu)
        binding.chatToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.message -> {
                    chatViewModel.addRandomReceivedMessage(args.userId)
                    true
                }
                else -> false
            }
        }

        binding.chatRecyclerView.adapter = chatAdapter

        binding.sendMessageButton.setOnClickListener {
            chatViewModel.addChatMessage(
                args.userId, true,
                binding.chatTextInputEditText.text.toString(),
            )
        }

        binding.chatTextInputEditText.setOnClickListener {
            scrollToBottom()
        }

        binding.chatTextInputEditText.setOnFocusChangeListener { _, focused ->
            if (focused) {
                scrollToBottom()
            }
        }
    }

    private fun scrollToBottom() {
        binding.chatRecyclerView.postDelayed({
            binding.chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)
        }, 500)
    }
}