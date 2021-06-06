import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'type'];
  title: string = "";

  users: User[] = [];

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.getAllBlocked();
    // this.getAllUnblocked();
  }

  getAllBlocked() {
    this.title = "Blocked users";
    console.log("Get all blocked users");

    this.userService.getAllBlocked()
    .subscribe(
      data => {
        this.users = data;
      }
    )
  }

  getAllUnblocked() {
    this.title = "Unblocked users";
    console.log("Get all blocked users");

    this.userService.getAllUnblocked()
    .subscribe(
      data => {
        this.users = data
      }
    )
  }

}
