package com.ricardo.muzmatchexercise.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.ricardo.muzmatchexercise.databinding.ContactsFragmentBinding
import com.ricardo.muzmatchexercise.di.ViewModelFactory
import com.ricardo.muzmatchexercise.ui.adapter.ContactsAdapter
import com.ricardo.muzmatchexercise.ui.viewmodel.ContactViewModel
import com.ricardo.muzmatchexercise.util.navigateAction
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ContactsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var binding: ContactsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactsFragmentBinding.inflate(inflater)

        contactViewModel =
            ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)

        contactViewModel.getAllContacts()
        lifecycleScope.launchWhenStarted {
            contactViewModel.contactsState.collect {
                val contactsAdapter = ContactsAdapter(it) { selectedContact ->
                    val action = ContactsFragmentDirections.openChatFragment()
                    action.userId = selectedContact.userId
                    navigateAction(action)
                }
                binding.contactsRecyclerView.adapter = contactsAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contactsRecyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(context, LinearLayout.VERTICAL)
            )
        }
    }
}