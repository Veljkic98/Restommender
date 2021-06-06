import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'type', 'action'];
  title: string = "";

  users: User[] = [];

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.getAllBlocked();
  }

  getAllBlocked() {
    this.title = "Blocked";

    this.userService.getAllBlocked()
      .subscribe(
        data => {
          this.users = data;
        }
      )
  }

  getAllUnblocked() {
    this.title = "Unblocked";

    this.userService.getAllUnblocked()
      .subscribe(
        data => {
          this.users = data
        }
      )
  }

  block(id: number) {
    this.userService.block(id)
    .subscribe(
      data => {
        this.getAllUnblocked();
      }
    )
  }

  unblock(id: number) {
    this.userService.unblock(id)
    .subscribe(
      data => {
        this.getAllBlocked();
      }
    )
  }

}
