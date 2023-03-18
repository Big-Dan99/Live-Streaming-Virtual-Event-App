import { Component, OnInit } from '@angular/core';
import { UserService } from '../myServices/user.service';
import { User } from '../myClasses/user';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css']
})
export class ProfilesComponent implements OnInit {

  constructor(private us: UserService) { }

  users! : User[] ;
  ngOnInit(): void {
    this.getAllUser();
  }

  public getAllUser(){
    this.us.getAllUser().subscribe(resp => {
      console.log(resp);
      
      this.users = resp;
    }) 
  }

}
