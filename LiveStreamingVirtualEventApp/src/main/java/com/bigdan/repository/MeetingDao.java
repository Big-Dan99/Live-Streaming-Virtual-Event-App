package com.bigdan.repository;

import com.bigdan.entity.Event;
import com.bigdan.entity.Meeting;
import com.bigdan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingDao extends JpaRepository<Meeting, Integer> {

    //    This method is used to find list of meeting using given  userid which are after current date.
    @Query("select m from Meeting m where m.organizer = ?1")
    List<Meeting> findMeetingByUser(User user);

    List<Meeting> findByPublishTrue();

    @Query("select m from Meeting m where m.organizer.userEmail = ?1")
    List<Meeting> findMeetingByUserId(String userdId);

    @Modifying
    @Query("update Meeting m set m.publish = :publish where m.meetingId = :meetingId")
    void updatePublishField(@Param("meetingId") Integer eventId, @Param("publish") boolean publish);
}
