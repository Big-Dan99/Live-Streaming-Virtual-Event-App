import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../myClasses/user';
import { UserService } from '../myServices/user.service';

@Component({
  selector: 'app-edit-user-role',
  templateUrl: './edit-user-role.component.html',
  styleUrls: ['./edit-user-role.component.css']
})
export class EditUserRoleComponent implements OnInit {

  constructor(private us: UserService, private ar: ActivatedRoute, private route : Router) { }

  user: User = new User();
  userId = this.ar.snapshot.params['userId'];

  ngOnInit(): void {
    this.getUserProfile();
  }

  public updateUserRole(){
    
    this.us.updateUserRole(this.user,this.userId).subscribe((response) => {
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
