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

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.sass'],
  providers: [ ProductService , CompanyService, CategoryService, TaxService]
})
export class ProductComponent implements OnInit {  
  isEmpty = false;
  modal_title = '';

  constructor(public modalService: NgbModal, public productService: ProductService, public companyService: CompanyService, public categoryService: CategoryService, public taxService: TaxService) { }

  ngOnInit() {
    this.getProducts();
    this.getCompanys();
    this.getCategorys();
    this.getTaxs();
  }

  openModal(content){
    this.modal_title = 'Añadir Producto';
    this.productService.selectedProduct = new Product();
    this.modalService.open(content, { centered: true, size: 'lg' });
  }

  addProduct(form: NgForm){
    if(this.productService.selectedProduct.id_product) {
      this.productService.putProduct(this.productService.selectedProduct)
        .subscribe(res => {
          this.resetForm(form);
          this.getProducts();
        });
    } else {
      this.productService.postProduct(form.value)
        .subscribe(res => {
          this.getProducts();
          this.resetForm(form);
        });
    }
    this.modalService.dismissAll();
  }

  editProduct(product: Product, content) {
    this.modal_title = 'Editar Producto';
    this.productService.selectedProduct = product;
    this.modalService.open(content, { centered: true, size: 'lg' });
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
        this.isEmpty = !(this.productService.products.length > 0);
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
