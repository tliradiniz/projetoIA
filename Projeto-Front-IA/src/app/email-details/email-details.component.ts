import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';
import { EmailService } from '../_services/email.service';

@Component({
  selector: 'app-email-details',
  templateUrl: './email-details.component.html',
  styleUrls: ['./email-details.component.css']
})
export class EmailDetailsComponent implements OnInit {
  currentUser = null;
  form: any = {};
  message = '';
  passwordUpdate = '';
  loggedUser = '';
  emailMessage = '';

  constructor(
    private userService: UserService,
    private emailService: EmailService,
    private route: ActivatedRoute,
    private router: Router,
    private token: TokenStorageService) { }

  ngOnInit() {
    this.message = '';
    this.getUser(this.route.snapshot.paramMap.get('id'));
    this.loggedUser = this.token.getUser();
  }

  getUser(id) {
    this.userService.get(id)
      .subscribe(
        data => {
          this.currentUser = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  sendMessage() {
    this.currentUser.password = this.passwordUpdate;
    this.emailService.sendMessage(this.currentUser.id, this.form)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'Mensagem enviada com sucesso!';
        },
        error => {
          console.log(error);
        });
  }
}
