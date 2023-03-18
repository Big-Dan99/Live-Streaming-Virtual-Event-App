import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
  constructor() {}

  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem('roles')!);
  }

  public setUserId(userId: any) {
    localStorage.setItem('userId', JSON.stringify(userId));
  }

  public getUserId(): [] {
    return JSON.parse(localStorage.getItem('userId')!);
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    return localStorage.getItem('jwtToken')!;
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }
  public isAdmin() {
    const roles: any[] = this.getRoles();
   return roles[0].roleName === 'Admin';
  }
  public isUser() {
    const roles: any[] = this.getRoles();
   return roles[0].roleName === 'Organizer';
  }
  
  // public isLoggedOut() {
  //   return this.getRoles() && this.getToken();
  // }
}
