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
    this.getCompanys();
  }

  addCompany(form: NgForm){
    if(this.companyService.selectedCompany.id_company) {
      this.companyService.putCompany(this.companyService.selectedCompany)
        .subscribe(res => {
          this.resetForm(form);
          this.getCompanys();
        });
    } else {
      this.companyService.postCompany(form.value)
        .subscribe(res => {
          this.getCompanys();
          this.resetForm(form);
        });
    }
  }

  editCompany(company: Company) {
    this.companyService.selectedCompany = company;
    console.log(this.companyService.selectedCompany);
  }

  resetForm(form?: NgForm) {
    if(form) {
      form.reset();
      this.companyService.selectedCompany = new Company();
    }
  }

  getCompanys() {
    this.companyService.getCompany()
      .subscribe(res => {
        this.companyService.companys = res as Company[];
      });
  }

}
