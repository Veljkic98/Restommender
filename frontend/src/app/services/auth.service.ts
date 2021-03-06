import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { JWT } from '../model/jwt.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // private headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': "Bearer " + JSON.parse(localStorage.getItem('user')).token });

  constructor(
    private http: HttpClient,
    public router: Router,
  ) { }

  login(email: string, password: string): Observable<JWT> {
    return this.http.post<JWT>("http://localhost:8080/api/auth/login", { username: email, password: password });
  }

  logOut(): void {
    localStorage.removeItem('user');

    this.router.navigate(['/login']);
    
    window.location.reload();
  }

  getId(): number {
    return localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).id : 'INVALID';
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('user') !== null;
  }

  getRole(): string {
    return localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).role : 'INVALID';
  }
}
