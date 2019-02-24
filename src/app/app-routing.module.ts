import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterPersonComponent  } from './components/register-person/register-person.component';

import { from } from 'rxjs';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register-person', component: RegisterPersonComponent  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
