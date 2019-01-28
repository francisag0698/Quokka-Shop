import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { catchError, map, tap, first } from 'rxjs/operators';

import { AuthService } from '../../services/auth.service';
import { AlertService } from '../../services/alert.service';
import { LoginAccount } from '../../models/login-account';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {
  account: LoginAccount
  constructor(
    private auth: AuthService,
    private alertService: AlertService
  ) {
    this.account = new LoginAccount();
  }

  ngOnInit() {
  }

  login() {
    this.auth.login(this.account).pipe(first())
      .subscribe(
        data => {
          console.log("DATA: " + (data as LoginAccount));
          location.reload();
        }, error => {
          this.alertService.error("Usuario o contraseña incorrectos.");
          console.log("ERROR: " + error.status);
        }
      )
  }
}
