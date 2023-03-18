import { Component, OnInit } from '@angular/core';
import { EventService } from '../myServices/event.service';
import { UserAuthService } from '../myServices/user-auth.service';
import { Event } from '../myClasses/event';
import { UserService } from '../myServices/user.service';
import { User } from '../myClasses/user';
import { Router } from '@angular/router';
import { Meeting } from '../myClasses/meeting';
import { MatDialog } from '@angular/material/dialog';
import { EventDescriptionComponent } from '../event-description/event-description.component';
import { MeetingDescriptionComponent } from '../meeting-description/meeting-description.component';

@Component({
  selector: 'app-organizer',
  templateUrl: './organizer.component.html',
  styleUrls: ['./organizer.component.css']
})
export class OrganizerComponent implements OnInit {

  constructor(private es: EventService,public dialog: MatDialog, private uas: UserAuthService, private us: UserService, private route: Router) { }
  user : User = new User();
  event: Event = new Event();
  events! : Event[] ;
  meetings! : Meeting[];
  userId = this.uas.getUserId();

  ngOnInit(): void {
    this.getEvents();
    this.getUserProfile();
    this.getMeetings();
  }

  public getEvents(){
    this.es.getEventForUser(this.userId).subscribe(resp => {
      this.events = resp;
    }) 
  }

  public getMeetings(){
    this.es.getMeetingForUser(this.userId).subscribe(resp => {
      console.log(resp);
      
      this.meetings = resp;
    }) 
  }

  public getUserProfile(){
    this.us.getProfile(this.userId).subscribe(response =>{
      this.user = response;
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

  public editUser(userId:  any){
     
    this.route.navigate(['/editUser',userId]);
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
 


}
