import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  isAdmin: String;

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    if (this.currentUser.isAdmin == "Y"){
      this.isAdmin = "Sim"
    }else{
      this.isAdmin = "Não"
    }
  }
}
