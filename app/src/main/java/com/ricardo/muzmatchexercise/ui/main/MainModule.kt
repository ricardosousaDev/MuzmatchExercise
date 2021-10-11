package com.ricardo.muzmatchexercise.ui.main

import com.ricardo.muzmatchexercise.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindContactsFragment(): ContactsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindChatFragment(): ChatFragment
}