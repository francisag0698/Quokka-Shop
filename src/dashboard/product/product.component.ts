import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { ProductService } from '../services/product.service';
import { CompanyService } from '../services/company.service';
import { CategoryService } from '../services/category.service';
import { TaxService } from '../services/tax.service';
import { Product } from '../models/product';
import { Company } from '../models/company';
import { Category } from '../models/category';
import { Tax } from '../models/tax';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.sass'],
  providers: [ ProductService , CompanyService, CategoryService, TaxService]
})
export class ProductComponent implements OnInit {

  

  constructor( public productService: ProductService, public companyService: CompanyService, public categoryService: CategoryService, public taxService: TaxService) { }

  ngOnInit() {
    this.getCompanys();
    this.getCategorys();
    this.getTaxs();
  }

  addProduct(form: NgForm){
    if(this.productService.selectedProduct.id_product) {

    } else {
      this.productService.postProduct(form.value)
        .subscribe(res => {
          this.resetForm(form);
        });
    }
  }

  editProduct(product: Product) {
    this.productService.selectedProduct = product;
  }

  resetForm(form?: NgForm) {
    if(form) {
      form.reset();
      this.productService.selectedProduct = new Product();
    }
  }

  getProducts() {
    this.productService.getProduct()
      .subscribe(res => {
        this.productService.products = res as Product[];
      });
  }

  getCompanys(){
    this.companyService.getCompany()
      .subscribe(res => {
      this.companyService.companys = res as Company[];
    });
  }

  getCategorys(){
    this.categoryService.getCategory()
      .subscribe(res => {
      this.categoryService.categorys = res as Category[];
    });
  }

  getTaxs(){
    this.taxService.getTax()
      .subscribe(res => {
      this.taxService.taxs = res as Tax[];
    });
  }


}
