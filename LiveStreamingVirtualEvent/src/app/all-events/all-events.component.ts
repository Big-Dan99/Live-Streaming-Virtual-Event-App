import { Component, OnInit } from '@angular/core';
import { EventService } from '../myServices/event.service';
import { Event } from '../myClasses/event';
import { Meeting } from '../myClasses/meeting';
import { User } from '../myClasses/user';
import { EventDescriptionComponent } from '../event-description/event-description.component';
import { MeetingDescriptionComponent } from '../meeting-description/meeting-description.component';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-all-events',
  templateUrl: './all-events.component.html',
  styleUrls: ['./all-events.component.css']
})
export class AllEventsComponent implements OnInit {

  constructor(private es: EventService, public dialog: MatDialog, private route: Router) { }
  meeting : Meeting = new Meeting();
 event : Event = new Event();
 
  events! : Event[] ;
  meetings! : Meeting[];

  ngOnInit(): void {
    this.getAllEvent(); 
    this.getAllMeeting();
    
  }

  public getAllEvent(){
    this.es.getAllEvents().subscribe(resp => {
      console.log(resp);
      
      this.events = resp;
    }) 
  }
  public getAllMeeting(){
    this.es.getAllMeetings().subscribe(resp => {
      console.log(resp);
      
      this.meetings = resp;
    }) 
  }

  public deleteEvent(eventId: any){
    
    this.es.deleteEvent(eventId).subscribe((response) => {
      console.log(response);
    })
      window.location.reload();
  }
  public deleteMeeting(meetingId: any){
    
    this.es.deleteMeeting(meetingId).subscribe((response) => {
      console.log(response);
    })
      window.location.reload();
  }

  public editEvent(eventid:  any){
     
    this.route.navigate(['/editEvent',eventid]);
  }
  public eventDescription(event: Event ) {
    // console.log(event);
    
    this.dialog.open(EventDescriptionComponent, {
      data: {
        desc: event.purpose,
        author: event.user,
      },
       height:'400px',
        width: '600px'
    });
  }

  public meetingDescription(meeting: Meeting ) {
    // console.log(meeting);
    
    this.dialog.open(MeetingDescriptionComponent, {
      data: {
        desc: meeting.description,
        author: meeting.organizer,
      },
       height:'400px',
        width: '600px'
    });
  }

  isPublished = false;

  public publier(){
    this.isPublished =true;
  }

  public nonPublier(){
    this.isPublished =false;
  }

  public publishEvent(eventId : any){
   this.es.publishEvent(this.event, eventId).subscribe((response) => {
    console.log(response);})
  }
  // public publishEvent(eventId : any, event : Event){
  //   this.es.publishEvent(event, eventId).subscribe((response) => {
  //    console.log(response);})
  //  }
  public publishMeeting(meetingId : any){
    this.es.publishMeeting(this.meeting, meetingId).subscribe((response) => {
     console.log(response);})
   }

 
  
 

}
