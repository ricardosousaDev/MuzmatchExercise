package com.ricardo.muzmatchexercise.data.repository

import com.ricardo.muzmatchexercise.data.local.ContactDao
import com.ricardo.muzmatchexercise.data.local.ContactEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) {
    suspend fun addContacts(contacts: List<ContactEntity>) = withContext(Dispatchers.IO) {
        async {
            // Create 1.5 seconds of suspense
            delay(1500)
            contactDao.addContacts(contacts)
        }
    }.await()

    fun getAllContacts(): Flow<List<ContactEntity>> {
        return contactDao.getAllContacts()
    }

    fun getContactByUserId(userId: Int): Flow<ContactEntity> {
        return contactDao.getContactByUserId(userId)
    }
}