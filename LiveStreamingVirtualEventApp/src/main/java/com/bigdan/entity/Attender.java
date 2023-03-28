package com.bigdan.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Attender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer attenderId;

//    @Email(message = "Should be a valid email, please check !")
    private String attenderEmail;

//    @Size(min = 2, max = 20, message = "Cannot be null, minimum length should be 2 and maximum length should be 20." )
    private String attenderFirstName;

//    @Size(min = 2, max = 20, message = "Cannot be null, minimum length should be 2 and maximum length should be 20." )
    private String attenderLastName;

//    @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
    private String attenderGoal;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "EVENT_ATTENDER",
            joinColumns = {
                    @JoinColumn(name = "ATTENDER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "EVENT_ID")
            }
    )
    private Event event;

    public Attender() {
    }

    public Attender(String attenderEmail, String attenderFirstName, String attenderLastName, String attenderGoal) {
        this.attenderEmail = attenderEmail;
        this.attenderFirstName = attenderFirstName;
        this.attenderLastName = attenderLastName;
        this.attenderGoal = attenderGoal;

    }

    public Integer getAttenderId() {
        return attenderId;
    }

    public void setAttenderId(Integer attenderId) {
        this.attenderId = attenderId;
    }

    public String getAttenderEmail() {
        return attenderEmail;
    }

    public void setAttenderEmail(String attenderEmail) {
        this.attenderEmail = attenderEmail;
    }

    public String getAttenderFirstName() {
        return attenderFirstName;
    }

    public void setAttenderFirstName(String attenderFirstName) {
        this.attenderFirstName = attenderFirstName;
    }

    public String getAttenderLastName() {
        return attenderLastName;
    }

    public void setAttenderLastName(String attenderLastName) {
        this.attenderLastName = attenderLastName;
    }

    public String getAttenderGoal() {
        return attenderGoal;
    }

    public void setAttenderGoal(String attenderGoal) {
        this.attenderGoal = attenderGoal;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
