import { Component, OnInit } from '@angular/core';
import { EventService } from '../myServices/event.service';
import { Event } from '../myClasses/event';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.css']
})
export class EditEventComponent implements OnInit {

  event: Event = new Event();
  eventId = this.ar.snapshot.params['eventid'];
 
  today= new Date().toJSON().split('T')[0];
  curr = new Date();
  tom = this.curr.getDate() + 2;
  first = this.curr.getDate() + 30;
   lday= new Date(this.curr.setDate(this.first)).toJSON().split('T')[0];

  constructor(private es: EventService, private ar: ActivatedRoute, private route : Router) { }

  ngOnInit(): void {
    this.getEventDetails();
  }

 

  public updateEvent(){
    
    this.es.updateEvent(this.event, this.eventId).subscribe((response) => {
      console.log(response);

    })
    
    this.route.navigate(['/']);
  }

  public getEventDetails(){
    this.es.getEventDetails(this.eventId).subscribe((resp)=> {
      console.log(resp);
      this.event = resp;
      
     })
  }

}
