package com.bigdan.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eventId;

    @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
    private String eventName;

    @Size(min = 5, max = 50, message = "Cannot be null, minimum length should be 5 and maximum length should be 50." )
    private String purpose;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Event date cannot be null")
    private LocalDate eventDate;

    @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
    private String slot;

    @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
    private String city;

    @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
    private String state;

    @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
    private String country;

    private boolean publish;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "EVENT_USER",
            joinColumns = {
                    @JoinColumn(name = "EVENT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_ID")
            }
    )
    private User user;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "EVENT_ATTENDER",
//            joinColumns = {
//                    @JoinColumn(name = "EVENT_ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "ATTENDER_ID")
//            }
//    )
//    private Set<Attender> attender;

    @Column(nullable = false, unique = true)
    private String eventLink;



    public Event() {
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public Event(Integer eventId, String eventName, String purpose, LocalDate eventDate, String slot, String city, String state, String country) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.purpose = purpose;
        this.eventDate = eventDate;
        this.slot = slot;
        this.city = city;
        this.state = state;
        this.country = country;
    }

//    public Set<Attender> getAttender() {
//        return attender;
//    }
//
//    public void setAttender(Set<Attender> attender) {
//        this.attender = attender;
//    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }


}
