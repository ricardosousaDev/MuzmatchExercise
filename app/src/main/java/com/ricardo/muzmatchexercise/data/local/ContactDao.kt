package com.ricardo.muzmatchexercise.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContacts(contact: List<ContactEntity>)

    @Query("SELECT * from Contact WHERE userId == :userId")
    fun getContactByUserId(userId: Int): Flow<ContactEntity>
}