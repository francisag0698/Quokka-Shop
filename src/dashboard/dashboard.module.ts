import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { DashboardRoutingModule } from './dashboard-routing.module';
import { MainComponent } from './main/main.component';
import { MenuComponent } from './menu/menu.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PreferencesComponent } from './preferences/preferences.component';
import { CompanyComponent } from './company/company.component';
import { CategoryComponent } from './category/category.component';

@NgModule({
  declarations: [MainComponent, MenuComponent, DashboardComponent, PreferencesComponent, CompanyComponent, CategoryComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    FormsModule,
    HttpClientModule
  ]
})
export class DashboardModule { }
