package com.github.joerodriguez.events.events

import com.github.joerodriguez.events.users.User
import java.util.*

data class Event(
        val id: Int,
        val activityType: String,
        val date: Date,
        val numberOfParticipants: Int,
        val creator: User
)
