package com.ricardo.muzmatchexercise.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.ricardo.muzmatchexercise.R
import com.ricardo.muzmatchexercise.databinding.MainFragmentBinding
import com.ricardo.muzmatchexercise.di.ViewModelFactory
import com.ricardo.muzmatchexercise.ui.viewmodel.MainLoadingUiState
import com.ricardo.muzmatchexercise.ui.viewmodel.MainViewModel
import com.ricardo.muzmatchexercise.util.navigate
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        if (sharedPreferences.getInt("loggedInUserId", 0) == 1000) {
            // We have our test data setup already - go to next screen
            navigate(R.id.contactsFragment)
            return binding.root
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect {
                when (it) {
                    is MainLoadingUiState.Loading -> binding.loadingProgressBar.show()
                    is MainLoadingUiState.Finished -> {
                        binding.loadingProgressBar.hide()
                        navigate(R.id.contactsFragment)
                    }
                    is MainLoadingUiState.None -> {
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadDataButton.setOnClickListener {
            // Fake login
            sharedPreferences.edit().putInt("loggedInUserId", 1000).apply()
            // Add fake contacts
            mainViewModel.addTestContacts()
        }
    }
}