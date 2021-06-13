import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Restaurant } from '../model/restaurant.model';

@Injectable({
  providedIn: 'root'
})
export class RestaurantsService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': "Bearer " + JSON.parse(localStorage.getItem('user')).token });

  private url: string = "http://localhost:8080/restaurants";

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<Restaurant[]>(`${this.url}`, { headers: this.headers }).toPromise();
  }

  getAllNoPromise() {
    return this.http.get<Restaurant[]>(`${this.url}`, { headers: this.headers });
  }
}
