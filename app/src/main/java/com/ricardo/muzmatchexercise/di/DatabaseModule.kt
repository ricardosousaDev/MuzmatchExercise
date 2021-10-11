package com.ricardo.muzmatchexercise.di

import androidx.room.Room
import com.ricardo.muzmatchexercise.MuzmatchExerciseApp
import com.ricardo.muzmatchexercise.data.AppDatabase
import com.ricardo.muzmatchexercise.data.local.ChatDao
import com.ricardo.muzmatchexercise.data.local.ContactDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: MuzmatchExerciseApp): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "muzmatchexercise.db").build()
    }

    @Singleton
    @Provides
    fun provideChatDao(database: AppDatabase): ChatDao = database.chatDao()

    @Singleton
    @Provides
    fun provideContactDao(database: AppDatabase): ContactDao = database.contactDao()
}