package com.ricardo.muzmatchexercise.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment

fun Fragment.navigate(resId: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
    NavHostFragment.findNavController(this).navigate(resId, bundle, navOptions)
}

fun Fragment.navigateAction(action: NavDirections) {
    NavHostFragment.findNavController(this).navigate(action)
}