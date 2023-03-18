import { Component, OnInit } from '@angular/core';
import { EventService } from '../myServices/event.service';
import { Event } from '../myClasses/event';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { UserAuthService } from '../myServices/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {
  
  event: Event = new Event();
  userId = this.uas.getUserId();

  constructor(private es: EventService, private uas: UserAuthService, private route: Router) { }

  ngOnInit(): void {
    console.log(this.uas.getUserId());
    
  }

  public addEvent(eventForm: NgForm) {
    this.es.createEvent(this.event,this.userId).subscribe(
      (response ) => {
        console.log(response);
        
        
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
    eventForm.resetForm();
  }

  // this.route.navigate(['/']);
}