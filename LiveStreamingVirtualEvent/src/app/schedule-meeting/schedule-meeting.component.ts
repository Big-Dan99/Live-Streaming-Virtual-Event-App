import { Component, OnInit } from '@angular/core';
import { EventService } from '../myServices/event.service';
import { Meeting } from '../myClasses/meeting';
import { UserAuthService } from '../myServices/user-auth.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-schedule-meeting',
  templateUrl: './schedule-meeting.component.html',
  styleUrls: ['./schedule-meeting.component.css']
})
export class ScheduleMeetingComponent implements OnInit {

  meeting: Meeting = new Meeting();
  userId = this.uas.getUserId();

  constructor(private es: EventService, private uas: UserAuthService) { }

  ngOnInit(): void {
    console.log(this.uas.getUserId());
    
  }

  public addMeeting(meetingForm: NgForm) {
    this.es.createMeeting(this.meeting,this.userId).subscribe(
      (response : Meeting) => {
        console.log(response);
       
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
    meetingForm.reset();
  }

}
