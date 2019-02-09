import { Component, OnInit } from '@angular/core';

import { NgForm } from "@angular/forms";

import { CategoryService } from "../services/category.service";
import { Category } from "../models/category";
import { Subscriber } from 'rxjs';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.sass'],
  providers: [CategoryService]
})
export class CategoryComponent implements OnInit {

  constructor( public categoryService: CategoryService ) { }

  ngOnInit() {
    this.getCategorys();
  }
  addCategory(form?: NgForm){
    if(this.categoryService.selectedCategory.id_category){
      this.categoryService.putCategory(this.categoryService.selectedCategory)
        .subscribe(res =>{
          this.resetForm(form);
          this.getCategorys();
      });
    }else{
      this.categoryService.postCategory(form.value)
      .subscribe(res => {
        this.getCategorys();
        this.resetForm(form);
      })
    }
  }
  getCategorys(){
    this.categoryService.getCategory()
    .subscribe(res =>{
      this.categoryService.category = res as Category[];
      console.log(res);

    });
  }

  editCategory(category: Category){
    this.categoryService.selectedCategory = category;

  }

  resetForm(form?: NgForm) {
    if(form) {
      form.reset();
      this.categoryService.selectedCategory = new Category();
    }
  }
  
}