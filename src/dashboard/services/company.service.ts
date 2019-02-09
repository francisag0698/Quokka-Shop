import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Company } from '../models/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  selectedCompany: Company;
  companys: Company[];

  readonly URL_API = '/api/company';

  constructor(private http: HttpClient) {
    this.selectedCompany = new Company();
  }

  postCompany(company: Company) {
    return this.http.post(this.URL_API, company);
  }

  getCompany(){
    return this.http.get(this.URL_API);
  }
  

  putCompany(company: Company) {
    return this.http.put(this.URL_API+`/${company.id_company}`, company);
  }

}
