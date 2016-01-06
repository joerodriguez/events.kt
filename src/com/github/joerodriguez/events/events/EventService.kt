package com.github.joerodriguez.events.events

import com.github.joerodriguez.events.users.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import kotlin.collections.hashMapOf

@Component
class EventService {

    @Autowired
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    fun getForUser(user: User): List<Event> {
        return jdbcTemplate.query(
                """
                    with relevant_events as (
                        select *
                        from events
                        where id in (select event_id from participants where user_id = :user_id)
                    ), event_counts as (
                        select e.id, count(*) as number_of_participants
                        from relevant_events e
                        left join participants p on p.event_id = e.id
                        group by 1
                    )

                    select
                        events.id as event_id,
                        occurs_on,
                        users.id as creator_id,
                        users.name as creator_name,
                        a.name as activity_type,
                        number_of_participants

                    from relevant_events events
                    join users on users.id = creator_id
                    join activity_types a on a.id = events.activity_type_id
                    left join event_counts on event_counts.id = events.id
                    order by occurs_on desc
                """,
                hashMapOf(
                        "user_id" to user.id
                ),
                { resultSet, i ->
                    Event(
                            id = resultSet.getInt("event_id"),
                            activityType = resultSet.getString("activity_type"),
                            date = resultSet.getDate("occurs_on"),
                            numberOfParticipants = resultSet.getInt("number_of_participants"),
                            creator = User(
                                    id = resultSet.getInt("creator_id"),
                                    name = resultSet.getString("creator_name")
                            )
                    )
                }
        )
    }

}