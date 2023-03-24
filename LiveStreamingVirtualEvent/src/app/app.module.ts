import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { OrganizerComponent } from './organizer/organizer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { UserService } from './myServices/user.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './myGuard/auth.guard';
import { AuthInterceptor } from './myGuard/auth.interceptor';
import { SignupComponent } from './signup/signup.component';
import { CreateadminComponent } from './createadmin/createadmin.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatGridListModule } from '@angular/material/grid-list';
import { EditEventComponent } from './edit-event/edit-event.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ScheduleMeetingComponent } from './schedule-meeting/schedule-meeting.component';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { MeetingDetailComponent } from './meeting-detail/meeting-detail.component';
import { MeetingDescriptionComponent } from './meeting-description/meeting-description.component';
import { EventDescriptionComponent } from './event-description/event-description.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AllEventsComponent } from './all-events/all-events.component';
import { ProfilesComponent } from './profiles/profiles.component';
import { EditUserRoleComponent } from './edit-user-role/edit-user-role.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    AdminComponent,
    OrganizerComponent,
    NavbarComponent,
    ForbiddenComponent,
    SignupComponent,
    CreateadminComponent,
    CreateEventComponent,
    EditEventComponent,
    EditUserComponent,
    ScheduleMeetingComponent,
    EventDetailComponent,
    MeetingDetailComponent,
    MeetingDescriptionComponent,
    EventDescriptionComponent,
    AllEventsComponent,
    ProfilesComponent,
    EditUserRoleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    MatGridListModule,
    MatDialogModule,
    MatFormFieldModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
