import { Component, OnInit } from '@angular/core';
import { Attender } from '../myClasses/attender';
import { UserService } from '../myServices/user.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-join-event',
  templateUrl: './join-event.component.html',
  styleUrls: ['./join-event.component.css'],
})
export class JoinEventComponent implements OnInit {
  mail = '^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$';

  submitted = false;
  attender: Attender = new Attender();
  constructor(private us: UserService, private ar: ActivatedRoute) {}

  eventId = this.ar.snapshot.params['eventId'];

  ngOnInit(): void {}

  public signup(signupAttender: NgForm) {
    this.us.joinEvent(this.attender, this.eventId).subscribe(
      (response: any) => {
        this.submitted = true;

        console.log(response);
        signupAttender.reset();
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  public clearForm(signupAttender: NgForm) {
    signupAttender.reset();
  }
}
