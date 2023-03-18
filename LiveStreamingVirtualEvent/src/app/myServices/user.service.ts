import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import { User } from '../myClasses/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = 'http://localhost:2809';

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' } );
  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) {}

  public login(loginData: any) {
    return this.httpclient.post(this.PATH_OF_API + '/authenticate', loginData, {
      headers: this.requestHeader,
    }); 
  } 

   public signup(user: User) :  Observable<any>{
    // const body = JSON.stringify(user);
    // const headers = new HttpHeaders()
    // .set('content-type' , 'application/json' )
    // .set('No-Auth', 'True');
    
     
    return this.httpclient.post('http://localhost:2809/registerNewUser',user , {
      headers: this.requestHeader, responseType: 'blob'
     }  );
  }
  // createUser(user : User): Observable<any>{
  //   return this.http.post('http://localhost:8080/user/saveUser',user ); 
          
  // }

  public signupadmin(user: User) {
    return this.httpclient.post<User>('http://localhost:2809/registerNewAdmin', user);
  }

  public getProfile(userId: any) : Observable<User>{
    return this.httpclient.get<User>(`http://localhost:2809/getProfile/${userId}`);
  }

  public getAllUser(){
    return this.httpclient.get<User[]>(`http://localhost:2809/getAllUser`);
  }

  public updateUser( user : User, userId: any) : Observable<Object>{
    return this.httpclient.put(`http://localhost:2809/updateUser/${userId}`, user);
  }

  public forUser() {
    return this.httpclient.get(this.PATH_OF_API + '/forUser', {
      responseType: 'text',
    });
  }


  public forAdmin() {
    return this.httpclient.get(this.PATH_OF_API + '/forAdmin', {
      responseType: 'text',
    });
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
