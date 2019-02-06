import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from '../dashboard/main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PreferencesComponent } from './preferences/preferences.component';
import { CompanyComponent } from './company/company.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: MainComponent,
    children: [
      {
        path: '',
        children: [
          { path: '', component: DashboardComponent },
          { path: 'preferences', 
            component: PreferencesComponent,
            children: [
              { path: '', component: CompanyComponent }
            ] 
          }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
