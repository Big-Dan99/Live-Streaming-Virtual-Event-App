import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../myServices/user.service';
import { User } from '../myClasses/user';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: User = new User();
  constructor(private us: UserService) { }

  ngOnInit(): void {
  }

  public signup(signupForm: NgForm) {
    

    this.us.signup(this.user).subscribe(
      (response : User) => {
        console.log(response);
        signupForm.reset();
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  public clearForm( signupForm: NgForm ) {
    signupForm.reset();
    
  }

}
