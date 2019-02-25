import { Component, OnInit } from '@angular/core';
import { RegisterPersonService } from '../../app/services/register-person.service';
import { RegisterPerson } from '../../app/models/register-person';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.sass'],
  providers: [ RegisterPersonService ]
})
export class CustomersComponent implements OnInit {

  constructor(public registerPersonService: RegisterPersonService) { }

  ngOnInit() {
  }

  getCustomers(){
    this.registerPersonService.getRegisterPerson()
      .subscribe(res => {
        this.registerPersonService.persons = res as RegisterPerson[];
      });
  }

}
