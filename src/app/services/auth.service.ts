import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { LoginAccount } from '../models/login-account';
import { catchError, map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthService {
  redirectURL: string;

  constructor(private http: HttpClient) { }

  login(Account: LoginAccount) {
    return this.http.post('/auth/session/login', Account).pipe(map(user => {
      return user;
    }));
  }
}
