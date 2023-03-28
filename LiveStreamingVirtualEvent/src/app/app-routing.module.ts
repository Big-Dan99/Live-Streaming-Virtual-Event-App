import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { OrganizerComponent } from './organizer/organizer.component';
import { AuthGuard } from './myGuard/auth.guard';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { SignupComponent } from './signup/signup.component';
import { CreateadminComponent } from './createadmin/createadmin.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { EditEventComponent } from './edit-event/edit-event.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ScheduleMeetingComponent } from './schedule-meeting/schedule-meeting.component';
import { MeetingDetailComponent } from './meeting-detail/meeting-detail.component';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { EventDescriptionComponent } from './event-description/event-description.component';
import { MeetingDescriptionComponent } from './meeting-description/meeting-description.component';
import { ProfilesComponent } from './profiles/profiles.component';
import { AllEventsComponent } from './all-events/all-events.component';
import { EditUserRoleComponent } from './edit-user-role/edit-user-role.component';
import { JoinEventComponent } from './join-event/join-event.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'admin', component: AdminComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  { path: 'organizer', component: OrganizerComponent ,  canActivate:[AuthGuard], data:{roles:['Organizer']} },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'createadmin', component: CreateadminComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  { path: 'profiles', component: ProfilesComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  { path: 'allEvents', component: AllEventsComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  { path: 'create-event', component: CreateEventComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'eventDetail', component: EventDetailComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'meetingDetail', component: MeetingDetailComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'eventDescription', component: EventDescriptionComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'meetingDescription', component: MeetingDescriptionComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'schedule-meeting', component: ScheduleMeetingComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'editEvent/:eventid', component: EditEventComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'editUser/:userId', component: EditUserComponent, canActivate:[AuthGuard], data:{roles:['Admin','Organizer']} },
  { path: 'joinEvent/:eventId', component: JoinEventComponent},
  { path: 'editUserRole/:userId', component: EditUserRoleComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  { path: 'forbidden', component: ForbiddenComponent }

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
