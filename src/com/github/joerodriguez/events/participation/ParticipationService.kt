package com.github.joerodriguez.events.participation

import com.github.joerodriguez.events.users.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import kotlin.collections.hashMapOf
import kotlin.collections.listOf
import kotlin.collections.mapOf

@Component
class ParticipationService {

    @Autowired
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    fun create(user: User, eventId: Int) {
        var a = mapOf("a" to "b", "c" to 2)
        jdbcTemplate.update(
                """
                    insert into
                    participants (user_id, event_id, joined_at)
                    values (:user_id, :event_id, now());
                """,
                mapOf(
                        "user_id" to user.id,
                        "event_id" to eventId
                )
        )
    }
}