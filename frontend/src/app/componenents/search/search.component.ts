import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  restaurantsSearch: FormGroup;
  music: string;
  accomodation: string;
  musicOptions = ['relaxing', 'loud'];
  accomodationOptions = ['udobno', ' tradicionalno'];

  
  constructor(private fb: FormBuilder) { }

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

}
