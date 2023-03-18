package com.bigdan.repository;

import com.bigdan.entity.Event;
import com.bigdan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {

    //    This method is used to find list of event using given  userid which are after current date.
            List<Event> findEventByUser(User user);

            List<Event> findByPublishTrue();

    @Modifying
    @Query("update Event e set e.publish = :publish where e.eventId = :eventId")
    void updatePublishField(@Param("eventId") Integer eventId, @Param("publish") boolean publish);

}
