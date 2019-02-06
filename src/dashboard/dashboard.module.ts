import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

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
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
