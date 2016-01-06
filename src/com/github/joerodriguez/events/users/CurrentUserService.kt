package com.github.joerodriguez.events.users

import org.springframework.stereotype.Component

@Component
class CurrentUserService {

    private val currentUser = User(name = "John", id = 1)

    fun get(): User = currentUser
}
