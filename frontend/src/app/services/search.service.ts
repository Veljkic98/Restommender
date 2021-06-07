import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Restaurant } from '../model/restaurant.model';
import { Search } from '../model/search.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': "Bearer " + JSON.parse(localStorage.getItem('user')).token });

  private url: string = "http://localhost:8080/api/search/"; 

  constructor(
    private http: HttpClient,
  ) { }

  getRestaurants(search: Search) {
    return this.http.post<Array<Restaurant>>(this.url, search, { headers: this.headers });
  }
}
