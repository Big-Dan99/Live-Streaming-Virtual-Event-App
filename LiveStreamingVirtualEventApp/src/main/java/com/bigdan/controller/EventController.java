package com.bigdan.controller;

import com.bigdan.entity.Event;
import com.bigdan.entity.Meeting;
import com.bigdan.entity.User;
import com.bigdan.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping({"/addNewEvent/{userId}"})
    @PreAuthorize("hasAnyRole('Admin','Organizer')")
    public ResponseEntity<String> addNewEvent(@Valid @RequestBody Event event, @PathVariable String userId, Errors errors) {
        String response = "";
        if (errors.hasErrors())
        {
            response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.ok(response);
        }
        else
        {
            response = eventService.addNewEvent(event, userId);
            return ResponseEntity.ok(response);
        }

    }
    @PostMapping({"/addNewMeeting/{userId}"})
    @PreAuthorize("hasAnyRole('Admin','Organizer')")
    public ResponseEntity<String> addNewMeeting(@Valid @RequestBody Meeting meeting, @PathVariable String userId, Errors errors) {
        if (errors.hasErrors())
        {
           String response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.ok(response);
        }
        else
        {

            return ResponseEntity.ok(eventService.addNewMeeting(meeting, userId));
        }

    }

    @GetMapping({"/getEvent/{userId}"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<List<Event>> getEventForUser(@PathVariable String userId){
        return  ResponseEntity.ok(eventService.getEvents(userId));
    }

    @GetMapping({"/getAllEvent"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<List<Event>> getAllEvent(){
        return  ResponseEntity.ok(eventService.getAllEvent());
    }

    @GetMapping({"/getPublishEvent"})
    public ResponseEntity<List<Event>> getPublishEvent(){
        return  ResponseEntity.ok(eventService.getPublishedEvent());
    }

    @GetMapping({"/getPublishMeeting"})
    public ResponseEntity<List<Meeting>> getPublishMeeting(){
        return  ResponseEntity.ok(eventService.getPublishedMeeting());
    }

    @GetMapping({"/getMeeting/{userId}"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<List<Meeting>> getMeetingForUser(@PathVariable String userId){
        return  ResponseEntity.ok(eventService.getMeetings(userId));
    }

    @GetMapping({"/getAllMeeting"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<List<Meeting>> getAllMeeting(){
        return  ResponseEntity.ok(eventService.getAllMeeting());
    }

    @DeleteMapping({"/delete/{eventId}"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<?> deleteEvent(@PathVariable("eventId") int eventId) {
        eventService.deleteEventById(eventId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/deletem/{meetingId}"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<?> deleteMeeting(@PathVariable("meetingId") int meetingId) {
        eventService.deleteMeetingById(meetingId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("updatEvent/{eventId}")
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<String> updateEvent(@PathVariable int eventId, @Valid @RequestBody Event event, Errors errors) {
        event.setEventId(eventId);
        if (errors.hasErrors())
        {
            String response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.ok(eventService.updateEvent(event));
        }
    }

    @PostMapping("/publishEvent/{eventId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> updateFeaturedField(@PathVariable("eventId") Integer eventId, @RequestParam("publish") boolean publish) {
        eventService.updatePublishField(eventId, publish);
        return ResponseEntity.ok("published event " + eventId);
    }

    @PostMapping("/publishMeeting/{meetingId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> updatePublishField(@PathVariable("meetingId") Integer meetingId, @RequestParam("publish") boolean publish) {
        eventService.updateMeetingPublishField(meetingId, publish);
        return ResponseEntity.ok("published event " + meetingId);
    }

//    @PutMapping("publishEvent/{eventId}")
//    @PreAuthorize("hasRole('Admin')")
//    public Event publishEvent(@PathVariable int eventId, @RequestBody Event pevent) {
//        pevent.setEventId(eventId);
//        return eventService.publishEvent(pevent);
//    }

    @GetMapping({"/getEventDetails/{eventId}"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<Event> getEventDetails(@PathVariable Integer eventId){
        return ResponseEntity.ok(eventService.getEventDetail(eventId));
    }

    @GetMapping({"/getMeetingDetails/{meetingId}"})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<Meeting> getMeetingDetails(@PathVariable Integer meetingId){
        return ResponseEntity.ok(eventService.getMeetingDetail(meetingId));
    }
}
