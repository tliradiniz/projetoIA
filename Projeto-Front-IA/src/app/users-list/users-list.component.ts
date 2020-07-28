import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: any;
  currentUser = null;
  currentIndex = -1;
  title = '';
  admin = '';

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.retrieveUsers();
  }

  retrieveUsers() {
    this.userService.getAll()
      .subscribe(
        data => {
          this.users = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList() {
    this.retrieveUsers();
    this.currentUser = null;
    this.currentIndex = -1;
  }

  setActiveUser(user, index) {
    this.admin = this.userService.checkIfAdmin(user);
    this.currentUser = user;
    this.currentIndex = index;
  }
}
