import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': "Bearer " + JSON.parse(localStorage.getItem('user')).token });

  private url: string = "http://localhost:8080/api/user/";

  constructor(
    private http: HttpClient
  ) { }

  getAllBlocked() {
    return this.http.get<Array<User>>(this.url + "blocked", { headers: this.headers });
  }

  getAllUnblocked() {
    return this.http.get<Array<User>>(this.url + "unblocked", { headers: this.headers });
  }
}
