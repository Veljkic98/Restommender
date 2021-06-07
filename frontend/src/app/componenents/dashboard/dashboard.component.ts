import { Component, OnInit } from '@angular/core';
import { Restaurant } from 'src/app/model/restaurant.model';
import { RestaurantsService } from 'src/app/services/restaurants.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  restaurants: Restaurant[];

  constructor(
    private restaurantsService: RestaurantsService,
  ) { }

  ngOnInit() {
    this.getAll();
  }

  async getAll() {
    this.restaurants = await this.restaurantsService.getAll();
  }

}
