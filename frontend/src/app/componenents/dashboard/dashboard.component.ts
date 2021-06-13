import { Component, Input, OnInit } from '@angular/core';
import { Reservation } from 'src/app/model/reservation.model';
import { Restaurant } from 'src/app/model/restaurant.model';
import { AuthService } from 'src/app/services/auth.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { RestaurantsService } from 'src/app/services/restaurants.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  restaurants: Restaurant[];
  numOfPersons: number = 1;
  rate: number = 1;

  receiveMessage($event) {
    console.log($event)
    this.restaurants = $event.restaurants;
    this.numOfPersons = $event.numOfPerson;
    this.rate = $event.rate;
  }


  constructor(
    private restaurantsService: RestaurantsService,
    private authService: AuthService,
    private resService: ReservationService,
  ) { }

  ngOnInit() {
    this.getAll();
  }

  async getAll() {
    this.restaurants = await this.restaurantsService.getAll();
  }

  makeReservation(id: number) {
    console.log(id)

    var reservation: Reservation = new Reservation();
    reservation.numOfPersons = this.numOfPersons;
    reservation.restaurantId = id;
    reservation.userId = this.authService.getId();

    this.resService.sendReservation(reservation, this.rate)
      .subscribe(
        data => {
          console.log("Sacuvali smo rezervaciju, print ispod");
          console.log(data);
        },
        error => {
          console.log("desila se greska");
          console.log(error)
        }
      )
  }

}
