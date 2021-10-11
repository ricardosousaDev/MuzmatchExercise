package com.ricardo.muzmatchexercise

import com.ricardo.muzmatchexercise.util.getMessageDay
import com.ricardo.muzmatchexercise.util.getMessageTime
import org.junit.Test

import org.junit.Assert.*

class DateUtilsTest {
    // Thursday 7/10/2021 @ 10:52:19
    private val messageTimestamp: Long = 1633603939

    @Test
    fun weekDayFormat_isCorrect() {
        assertEquals("Thursday", messageTimestamp.getMessageDay())
    }

    @Test
    fun timeFormat_isCorrect() {
        assertEquals("10:52", messageTimestamp.getMessageTime())
    }
}