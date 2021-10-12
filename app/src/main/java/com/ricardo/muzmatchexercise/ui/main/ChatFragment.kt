package com.ricardo.muzmatchexercise.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.ricardo.muzmatchexercise.data.local.ChatEntity
import com.ricardo.muzmatchexercise.databinding.ChatFragmentBinding
import com.ricardo.muzmatchexercise.di.ViewModelFactory
import com.ricardo.muzmatchexercise.ui.adapter.ChatAdapter
import com.ricardo.muzmatchexercise.ui.viewmodel.ChatViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class ChatFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val args: ChatFragmentArgs by navArgs()

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var binding: ChatFragmentBinding

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
                binding.chatRecyclerView.adapter = ChatAdapter(it)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendMessageButton.setOnClickListener {
            val timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).epochSecond
            chatViewModel.addChatMessage(ChatEntity(fromUserId = args.userId,
                isReply = true, message = binding.chatTextInputEditText.text.toString(),
            timestamp = timestamp))
        }
    }
}