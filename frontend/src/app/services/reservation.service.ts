import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reservation } from '../model/reservation.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': "Bearer " + JSON.parse(localStorage.getItem('user')).token });

  private url: string = "http://localhost:8080/api/reservations";

  constructor(
    private http: HttpClient
  ) { }

  sendReservation(res: Reservation, rate: number) {
    return this.http.post(`${this.url}/${rate}`, res, {headers: this.headers});
  }
}
