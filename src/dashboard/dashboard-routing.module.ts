import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from '../dashboard/main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PreferencesComponent } from './preferences/preferences.component';
import { CompanyComponent } from './company/company.component';
import { CategoryComponent } from './category/category.component';
import { InventoryComponent } from './inventory/inventory.component';
import { ProductComponent } from './product/product.component';
import { TaxComponent } from './tax/tax.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: MainComponent,
    children: [
      {
        path: '',
        children: [
          { path: '', component: DashboardComponent },
          {
            path: 'inventory',
            component: InventoryComponent,
            children: [
              { path: '', component: ProductComponent },
              { path: 'tax', component: TaxComponent }
            ]
          },
          { path: 'preferences', 
            component: PreferencesComponent,
            children: [
              { path: '', component: CompanyComponent },
              { path: 'category', component: CategoryComponent }
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
