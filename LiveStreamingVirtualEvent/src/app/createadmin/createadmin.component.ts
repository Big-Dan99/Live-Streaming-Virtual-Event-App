import { Component, OnInit } from '@angular/core';
import { User } from '../myClasses/user';
import { UserService } from '../myServices/user.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-createadmin',
  templateUrl: './createadmin.component.html',
  styleUrls: ['./createadmin.component.css']
})
export class CreateadminComponent implements OnInit {

  user: User = new User();
  constructor(private us: UserService) { }

  ngOnInit(): void {
  }

  public signupadmin(signupForm: NgForm) {
    

    this.us.signupadmin(this.user).subscribe(
      (response : User) => {
        console.log(response);
        
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
    signupForm.reset();
  }

  public clearForm( signupForm: NgForm ) {
    signupForm.reset();
    
  }

}
