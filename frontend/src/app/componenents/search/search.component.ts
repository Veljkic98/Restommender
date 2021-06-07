import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
  accomodationOptions = ['udobno', ' tradicionalno'];

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private searchService: SearchService,
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
  }

  search() {
    var search = this.getSearchObj();

    console.log(search)

    this.searchService.getRestaurants(search)
    .subscribe(
      data => {
        console.log(data);
      }
    )
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

}



