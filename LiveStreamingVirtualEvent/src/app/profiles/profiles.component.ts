import { Component, OnInit } from '@angular/core';
import { UserService } from '../myServices/user.service';
import { User } from '../myClasses/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css']
})
export class ProfilesComponent implements OnInit {

  constructor(private us: UserService, private route: Router) { }

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

  public editUserole(userId: any){
    
      this.route.navigate(['/editUserRole',userId]);

  }

  public deleteUser(userId: any){
    
    this.us.deleteUser(userId).subscribe((response) => {
      console.log(response);
    })
      window.location.reload();
  }

}
