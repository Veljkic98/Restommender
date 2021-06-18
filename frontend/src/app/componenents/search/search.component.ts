import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Restaurant } from 'src/app/model/restaurant.model';
import { Search } from 'src/app/model/search.model';
import { AuthService } from 'src/app/services/auth.service';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  restaurantsSearch: FormGroup;
  music: string;
  accomodation: string;
  name: string = "";
  numOfPerson: number = 1;
  location: number = 1;
  rate: number = 0;
  musicOptions = ['relaxing', 'loud'];
  accomodationOptions = ['udobno', 'tradicionalno'];

  @Output() messageEvent = new EventEmitter<{ restaurants: Array<Restaurant>, numOfPerson: number, rate: number }>();

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private searchService: SearchService,
    private snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.restaurantsSearch = this.fb.group({
      smokingArea: false,
      nonSmokingArea: false,
      alcoholicDrinks: false,
      nonAlcoholicDrinks: false,
      petFriendly: false,
      kidFriendly: false,
    });

    this.getAll();
  }

  restaurants: Restaurant[] = [];

  getAll() {
    var search = this.getSearchObj();
    
    this.searchService.getRestaurants(search)
    .subscribe(
      data => {
        this.restaurants = data;

        // send restaurants to another component
        this.messageEvent.emit({ restaurants: data, numOfPerson: this.numOfPerson, rate: this.rate });
      }, error => {
        if (error.error) {
          this.openSnackBar(error.error);

          this.authService.logOut();
        }
      }
    )
  }

  sendData() {
    this.messageEvent.emit({ restaurants: this.restaurants, numOfPerson: this.numOfPerson, rate: this.rate });
  }

  getSearchObj(): Search {

    var search: Search = new Search();

    search.accomodation = this.accomodation;
    search.alcoholicDrinks = this.restaurantsSearch.get("alcoholicDrinks").value;
    search.kidFriendly = this.restaurantsSearch.get("kidFriendly").value;
    search.location = this.location;
    search.music = this.music;
    search.name = this.name;
    search.nonAlcoholicDrinks = this.restaurantsSearch.get("nonAlcoholicDrinks").value;
    search.nonSmokingArea = this.restaurantsSearch.get("nonSmokingArea").value;
    search.numOfPersons = this.numOfPerson;
    search.petFriendly = this.restaurantsSearch.get("petFriendly").value;
    search.rate = this.rate;
    search.smokingArea = this.restaurantsSearch.get("smokingArea").value;
    search.userId = this.authService.getId();

    return search;
  }

  openSnackBar(message: string): void {
    this.snackBar.open(message, 'Dismiss', {
      verticalPosition: 'top',
      duration: 4000,
    });
  }

}



