package com.bigdan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class MailNotif {




        @Autowired
        private JavaMailSender javaMailSender;

        public static int noOfQuickServiceThreads = 20;

        private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        public void sendPublishEventEmail( String email, int eventId, String fname, String lname,String eventName,
                                        LocalDate eventDate) {
            logger.info("Inside sendEmail1() method of {}", this.getClass());
            quickService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        SimpleMailMessage msg = new SimpleMailMessage();
                        msg.setTo(email);
                        msg.setSubject(
                                "Your Event with event id " + eventId + " has been successfully published.");
                        msg.setText("Dear " + fname +" "+ lname + " \nYour planified event "+eventName+" on "+eventDate
                                + " has been published successfully. \nEverybody can see it now ! "
                                + ". \n \n \nThanks and Regards \nLife streaming virtual event Admin");
                        javaMailSender.send(msg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    public void sendPublishMeetingEmail( String email, int meetingId, String fname, String lname,String title,
                                       LocalDate meetingDate) {
        logger.info("Inside sendEmail1() method of {}", this.getClass());
        quickService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(email);
                    msg.setSubject(
                            "Your Meeting with meeting id " + meetingId + " has been successfully published.");
                    msg.setText("Dear " + fname +" "+ lname + " \nYour planified meeting "+title+" on "+meetingDate
                            + " has been published successfully. \nEverybody can see it now ! "
                            + ". \n \n \nThanks and Regards \nLife streaming virtual event Admin");
                    javaMailSender.send(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

        public void sendUnpublishEventEmail(String email, int eventId, String fname, String lname,String eventName,
                                       LocalDate eventDate) {
            logger.info("Inside sendEmail2() method of {}", this.getClass());
            quickService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        SimpleMailMessage msg = new SimpleMailMessage();
                        msg.setTo(email);
                        msg.setSubject(
                                "Your event with event id " + eventId + " has been  unpublished.");
                        msg.setText("Dear " + fname +" "+ lname + " \n" + eventName +" event on "
                                +  eventDate
                                + " has been unpublish . \n \n \nThanks and Regards \nLife streaming virtual event Admin");
                        javaMailSender.send(msg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    public void sendUnpublishMeetingEmail(String email, int meetingId, String fname, String lname,String title,
                                        LocalDate meetingDate) {
        logger.info("Inside sendEmail2() method of {}", this.getClass());
        quickService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(email);
                    msg.setSubject(
                            "Your meeting with meeting id " + meetingId + " has been  unpublished.");
                    msg.setText("Dear " + fname +" "+ lname + " \n" + title +" meeting on "
                            +  meetingDate
                            + " has been unpublish . \n \n \nThanks and Regards \nLife streaming virtual event Admin");
                    javaMailSender.send(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }



    }




