package com.github.joerodriguez.events.participation

import com.github.joerodriguez.events.users.CurrentUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController

@RestController
class ParticipationController {

    @Autowired
    private lateinit var currentUserService: CurrentUserService

    @Autowired
    private lateinit var participationService: ParticipationService

    @RequestMapping(method = arrayOf(POST), value = "/events/{eventId}/participate")
    fun participate(
            @PathVariable eventId: Int
    ): ResponseEntity<Unit> {

        participationService.create(currentUserService.get(), eventId);

        return ResponseEntity(HttpStatus.CREATED);
    }

}
