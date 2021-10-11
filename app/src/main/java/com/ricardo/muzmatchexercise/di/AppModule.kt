package com.ricardo.muzmatchexercise.di

import android.content.Context
import android.content.SharedPreferences
import com.ricardo.muzmatchexercise.MuzmatchExerciseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
internal class AppModule {

    @Provides
    fun provideContext(application: MuzmatchExerciseApp): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(applicationContext: Context): SharedPreferences {
        return applicationContext.getSharedPreferences("default", Context.MODE_PRIVATE)
    }
}