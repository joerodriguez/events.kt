package com.github.joerodriguez.events.events

import com.github.joerodriguez.events.users.CurrentUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
class EventsController {

    @Autowired
    private lateinit var currentUserService: CurrentUserService

    @Autowired
    private lateinit var eventsService: EventService

    @RequestMapping(method = arrayOf(GET), value = "/events")
    fun myEvents(): List<Event> {
        return eventsService.getForUser(currentUserService.get());
    }
}
