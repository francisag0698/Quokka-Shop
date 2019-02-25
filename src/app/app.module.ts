import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AlertsModule } from 'angular-alert-module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AlertComponent } from './components/alert/alert.component';
import { RegisterPersonComponent  } from './components/register-person/register-person.component';
import { MainPageComponent } from './components/main-page/main-page.component';

import { DashboardModule } from '../dashboard/dashboard.module';

import { environment } from '../environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AlertComponent,
    RegisterPersonComponent,
    MainPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DashboardModule,
    NgbModule,
    AlertsModule.forRoot()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
