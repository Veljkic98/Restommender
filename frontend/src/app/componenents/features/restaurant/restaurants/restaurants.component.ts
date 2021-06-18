import { Component, OnInit } from '@angular/core';
import { RestaurantsService } from 'src/app/services/restaurants.service';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.css']
})
export class RestaurantsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'location', 'music', 'accomodation', 'rate', 'resNum'];

  restaurants = [];

  constructor(
    private restaurantService: RestaurantsService,
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.restaurantService.getAllNoPromise()
    .subscribe(
      data => {
        console.log(data);
        this.restaurants = data;
      }, error => {
        console.log("Error: " + error.error);
      }
    );
  }

}
