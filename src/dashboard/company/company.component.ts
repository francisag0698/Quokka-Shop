import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { CompanyService } from '../services/company.service';
import { Company } from '../models/company';


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.sass'],
  providers: [ CompanyService ]
})
export class CompanyComponent implements OnInit {

  constructor(public companyService: CompanyService) { }

  ngOnInit() {
  }

  addCompany(form?: NgForm){
    if(form.value.id) {
      this.companyService.putCompany(form.value)
        .subscribe(res => {
          this.resetForm(form);
        });
    } else {
      this.companyService.postCompany(form.value)
        .subscribe(res => {
          this.resetForm(form);
        });
    }
  }

  resetForm(form?: NgForm) {
    if(form) {
      form.reset();
      this.companyService.selectedCompany = new Company();
    }
  }

}
