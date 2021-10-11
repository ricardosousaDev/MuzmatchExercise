package com.ricardo.muzmatchexercise.di

import com.ricardo.muzmatchexercise.MainActivity
import com.ricardo.muzmatchexercise.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}