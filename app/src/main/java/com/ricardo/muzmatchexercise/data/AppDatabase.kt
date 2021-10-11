package com.ricardo.muzmatchexercise.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ricardo.muzmatchexercise.data.local.ChatDao
import com.ricardo.muzmatchexercise.data.local.ChatEntity
import com.ricardo.muzmatchexercise.data.local.ContactDao
import com.ricardo.muzmatchexercise.data.local.ContactEntity

@Database(entities = [ChatEntity::class, ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

    abstract fun contactDao(): ContactDao
}