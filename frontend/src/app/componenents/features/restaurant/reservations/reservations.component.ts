import { Component, OnInit } from '@angular/core';
import { ReservationView } from 'src/app/model/reservation-view.model';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'restaurant', 'numOfPerson', 'discount'];

  reservations: ReservationView[] = [];

  constructor(
    private resService: ReservationService,
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.resService.getAllNoPromise()
    .subscribe(
      data => {
        console.log(data);
        this.reservations = data;
      }
    )
  }

}
