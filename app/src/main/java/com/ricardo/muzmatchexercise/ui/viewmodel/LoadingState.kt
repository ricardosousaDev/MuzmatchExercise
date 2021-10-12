package com.ricardo.muzmatchexercise.ui.viewmodel

sealed class LoadingState {
    object None : LoadingState()
    object Loading : LoadingState()
    object Finished : LoadingState()
}