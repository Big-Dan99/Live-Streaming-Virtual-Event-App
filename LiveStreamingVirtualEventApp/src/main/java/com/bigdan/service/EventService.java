package com.bigdan.service;

import com.bigdan.entity.*;
import com.bigdan.repository.AttenderDao;
import com.bigdan.repository.EventDao;
import com.bigdan.repository.MeetingDao;
import com.bigdan.repository.UserDao;
import com.bigdan.util.MailNotif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EventService {
    @Autowired
    EventDao eventDao;

    @Autowired
    MeetingDao meetingDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MailNotif mailNotif;

    @Autowired
    private AttenderDao attenderDao;

//    public String joinEvent(Attender attender, Integer eventId){
//
//        registerNewAttender(attender);
//        Event event = eventDao.findById(eventId).orElseThrow(()-> new IllegalArgumentException("Invalid eventId"));
//
//        if (event != null) {
////            event.setEventName(event.getEventName());
////            event.setPurpose(event.getPurpose());
////            event.setEventDate(event.getEventDate());
////            event.setSlot(event.getSlot());
////            event.setCity(event.getCity());
////            event.setState(event.getState());
////            event.setCountry(event.getCountry());
////            event.setPublish(event.isPublish());
////            event.setEventLink(event.getEventLink());
//
//            Set<Attender> attenders = new HashSet<>();
//            attenders.add(attender);
//            event.setAttender(attenders);
//            mailNotif.sendJoinEventEmail(attender.getAttenderEmail(), eventId, attender.getAttenderFirstName(), attender.getAttenderLastName(), event.getEventName(),event.getEventDate(),event.getEventLink());
//            // Update other fields as needed
//            eventDao.save(event);
//            return attender.getAttenderEmail();
//        }
//
//            return null;
//
//
//    }

    public String registerNewAttender(Attender attender) {

        attenderDao.save(attender);
//        mailNotif.sendJoinEventEmail(attender.getAttenderEmail(), eventId, attender.getAttenderFirstName(), attender.getAttenderLastName(), event.getEventName(),event.getEventDate(),event.getEventLink());
        return attender.getAttenderEmail();

//        }


    }

    public String addNewEvent(Event event, String userId){
        User user = userDao.findById(userId).orElseThrow(()-> new IllegalArgumentException("Invalid userId"));

            event.setUser(user);
            event.setPublish(false);

        Event evt = eventDao.save(event);
        return evt.getEventName();
    }

    public String addNewMeeting(Meeting meeting, String userId){
        User user = userDao.findById(userId).orElseThrow(()-> new IllegalArgumentException("Invalid userId"));
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Convert UUID to a string
        String meetingLink = "https://meet.google.com/" + uuid.toString();

        meeting.setOrganizer(user);
        meeting.setMeetingLink(meetingLink);
        meeting.setPublish(false);

        Meeting meet = meetingDao.save(meeting);
        return meet.getTitle();
    }

    public List<Event> getEvents(String userId){
        User user = userDao.findById(userId).orElseThrow(()-> new IllegalArgumentException("Invalid userId"));

        return eventDao.findEventByUser(user);}

    public List<Meeting> getMeetings(String userId){
        User user = userDao.findById(userId).orElseThrow(()-> new IllegalArgumentException("Invalid userId"));

        return meetingDao.findMeetingByUser(user);}

    public List<Event> getAllEvent(){
        return eventDao.findAll();
    }


    public List<Meeting> getAllMeeting(){
        return meetingDao.findAll();
    }

    @Transactional
    public void updatePublishField(Integer eventId, boolean publish) {
        eventDao.updatePublishField(eventId, publish);
        Event event = eventDao.findById(eventId).get();

        if (publish == true){
            mailNotif.sendPublishEventEmail(event.getUser().getUserEmail(), eventId, event.getUser().getUserFirstName(), event.getUser().getUserLastName()
            , event.getEventName(), event.getEventDate());
        }else {
            mailNotif.sendUnpublishEventEmail(event.getUser().getUserEmail(), eventId, event.getUser().getUserFirstName(), event.getUser().getUserLastName()
                    , event.getEventName(), event.getEventDate());
        }

    }

    @Transactional
    public void updateMeetingPublishField(Integer meetingId, boolean publish) {
        meetingDao.updatePublishField(meetingId, publish);
        Meeting meeting = meetingDao.findById(meetingId).get();

        if(publish == true){
            mailNotif.sendPublishMeetingEmail(meeting.getOrganizer().getUserEmail(), meetingId, meeting.getOrganizer().getUserFirstName(),
                   meeting.getOrganizer().getUserLastName(),meeting.getTitle(), meeting.getMeetingDay() );
        }else {
            mailNotif.sendUnpublishMeetingEmail( meeting.getOrganizer().getUserEmail(), meetingId, meeting.getOrganizer().getUserFirstName(),
                    meeting.getOrganizer().getUserLastName(),meeting.getTitle(), meeting.getMeetingDay() );
        }
    }

    public List<Event> getPublishedEvent(){
        return eventDao.findByPublishTrue();
    }

    public List<Meeting> getPublishedMeeting(){
        return meetingDao.findByPublishTrue();
    }

//    public void deleteEventById(Integer eventId){
//        eventDao.deleteById(eventId);
//    }
public void deleteEventById(int eventId) {
    Event event = eventDao.findById(eventId).orElse(null);

//        event.getUser().clear(); // remove all associated users
    event.setUser(null);
        eventDao.deleteById(eventId); // delete the event

}
public void deleteEventsByUserId(String userId){
    List<Event>  events = eventDao.findEventByUserId(userId); // All related events to this user

     eventDao.deleteAll(events);
}

    public void deleteMeetingsByUserId(String userId){
        List<Meeting>  meetings = meetingDao.findMeetingByUserId(userId); // All related meetings to this user

        meetingDao.deleteAll(meetings);
    }

    public void deleteMeetingById(int meetingId) {

            meetingDao.deleteById(meetingId); // delete the event
    }

    public String updateEvent(Event event) {
        Event existingEvent = eventDao.findById(event.getEventId()).orElse(null);
        if (existingEvent != null) {
            existingEvent.setEventName(event.getEventName());
            existingEvent.setPurpose(event.getPurpose());
            existingEvent.setEventDate(event.getEventDate());
            existingEvent.setSlot(event.getSlot());
            existingEvent.setCity(event.getCity());
            existingEvent.setState(event.getState());
            existingEvent.setCountry(event.getCountry());
            existingEvent.setPublish(event.isPublish());
            existingEvent.setEventLink(event.getEventLink());
            // Update other fields as needed
             eventDao.save(existingEvent);
             return existingEvent.getEventName();
        }
        return null;
    }



    public Event getEventDetail(Integer eventId){
        return eventDao.findById(eventId).get();
    }

    public Meeting getMeetingDetail(Integer meetingId){
        return meetingDao.findById(meetingId).get();
    }
}
