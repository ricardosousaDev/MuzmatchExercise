package com.ricardo.muzmatchexercise.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    protected val mutableUiState: MutableStateFlow<LoadingState> =
        MutableStateFlow(LoadingState.None)
    val uiState: StateFlow<LoadingState> = mutableUiState
}