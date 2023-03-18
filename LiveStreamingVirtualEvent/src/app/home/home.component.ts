import { Component, OnInit } from '@angular/core';
import { EventService } from '../myServices/event.service';
import { UserAuthService } from '../myServices/user-auth.service';
import { Event } from '../myClasses/event';
import { EventDescriptionComponent } from '../event-description/event-description.component';
import { MatDialog } from '@angular/material/dialog';
import { Meeting } from '../myClasses/meeting';
import { MeetingDescriptionComponent } from '../meeting-description/meeting-description.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private es: EventService, public dialog: MatDialog) { }

  events! : Event[] ;
  meetings! : Meeting[];
  ngOnInit(): void {
    this.getPublishEvent();
    this.getPublishMeeting();

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

  public getPublishEvent(){
    this.es.getPublishEvent().subscribe(resp => {
      console.log(resp);
      
      this.events = resp;
    }) 
  }

  public getPublishMeeting(){
    this.es.getPublishMeeting().subscribe(resp => {
      console.log(resp);
      
      this.meetings = resp;
    }) 
  }
}
