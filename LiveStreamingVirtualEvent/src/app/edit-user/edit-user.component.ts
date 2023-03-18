import { Component, OnInit } from '@angular/core';
import { UserService } from '../myServices/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../myClasses/user';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  constructor(private us: UserService, private ar: ActivatedRoute, private route : Router) { }

  user: User = new User();
  userId = this.ar.snapshot.params['userId'];

  ngOnInit(): void {
    this.getUserProfile();
  }

  public updateUser(){
    
    this.us.updateUser(this.user,this.userId).subscribe((response) => {
      console.log(response);
      
      
    })
    this.route.navigate(['/']);
  }

  public getUserProfile(){
    this.us.getProfile(this.userId).subscribe((data) => {
      this.user = data;
    })
  }

}
