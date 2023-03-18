package com.bigdan.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meetingId;

    @Column(nullable = false)
    @Size(min = 2, max = 20, message = "Cannot be null, minimum length should be 2 and maximum length should be 20." )
    private String title;

    @Column(nullable = false)
    @Size(min = 10, max = 50, message = "Cannot be null, minimum length should be 10 and maximum length should be 50." )
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @Column(nullable = false)
    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @Column(nullable = false)
    @NotNull(message = "Meeting day cannot be null")
    private LocalDate meetingDay;

    @Column(nullable = false, unique = true)
    private String meetingLink;

    private boolean publish;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    public Meeting() {
    }

    public Meeting(Integer meetingId, String title, String description, LocalTime startTime, LocalTime endTime, String meetingLink, User organizer) {
        this.meetingId = meetingId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingLink = meetingLink;
        this.organizer = organizer;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public LocalDate getMeetingDay() {
        return meetingDay;
    }

    public void setMeetingDay(LocalDate meetingDay) {
        this.meetingDay = meetingDay;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
}
