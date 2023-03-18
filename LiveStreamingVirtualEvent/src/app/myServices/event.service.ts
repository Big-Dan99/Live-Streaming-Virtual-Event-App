import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import { Event } from '../myClasses/event';
import { Observable } from 'rxjs';
import { Meeting } from '../myClasses/meeting';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  constructor( private httpclient: HttpClient,
    private userAuthService: UserAuthService) { }

  public createEvent(event: Event, userId: any) {
    return this.httpclient.post<Event>('http://localhost:2809/addNewEvent/'+userId, event);
  }
  public createMeeting(meeting: Meeting, userId: any) {
    return this.httpclient.post<Meeting>('http://localhost:2809/addNewMeeting/'+userId, meeting);
  }
  public getAllEvents(){
    return this.httpclient.get<Event[]>(`http://localhost:2809/getAllEvent`);
  }

  public getPublishEvent(){
    return this.httpclient.get<Event[]>(`http://localhost:2809/getPublishEvent`,{
      headers: this.requestHeader,
    });
  }
  public getPublishMeeting(){
    return this.httpclient.get<Meeting[]>(`http://localhost:2809/getPublishMeeting`,{
      headers: this.requestHeader,
    });
  }
  
  public getAllMeetings(){
    return this.httpclient.get<Meeting[]>(`http://localhost:2809/getAllMeeting`);
  }

  getEventForUser (userId : any){
    return this.httpclient.get<Event[]>(`http://localhost:2809/getEvent/${userId}`);
  }
  getMeetingForUser (userId : any){
    return this.httpclient.get<Meeting[]>(`http://localhost:2809/getMeeting/${userId}`);
  }

  public deleteEvent(eventId: any): Observable<Event> {
    return this.httpclient.delete<Event>(`http://localhost:2809/delete/${eventId}`);
  }
  public deleteMeeting(meetingId: any): Observable<Meeting> {
    return this.httpclient.delete<Meeting>(`http://localhost:2809/deletem/${meetingId}`);
  }

  public updateEvent( event : Event, eventId: any) : Observable<Object>{
    return this.httpclient.put(`http://localhost:2809/updatEvent/${eventId}`, event);
  }

  public publishEvent( event : Event, eventId: any) : Observable<Object>{
    let param = new HttpParams();
    param = param.set('publish', event.publish);
    return this.httpclient.post(`http://localhost:2809/publishEvent/${eventId}`, param, {responseType: 'json'});
  }
  
  public publishMeeting( meeting : Meeting, meetingId: any) : Observable<Object>{
    let param = new HttpParams();
    param = param.set('publish', meeting.publish);
    return this.httpclient.post(`http://localhost:2809/publishMeeting/${meetingId}`, param, {responseType: 'json'});
  }

  public getEventDetails(eventId: any){
    return this.httpclient.get<Event>('http://localhost:2809/getEventDetails/'+eventId);
  } 

  public getMeetingDetails(meetingId: any){
    return this.httpclient.get<Meeting>('http://localhost:2809/getMeetingDetails/'+meetingId);
  }


  
  public roleMatch(allowedRoles: string | any[]): any {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();

    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].roleName === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          } else {
            return isMatch;
          }
        }
      }
    }
  }
}
