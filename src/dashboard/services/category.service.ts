import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {Category} from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  selectedCategory: Category;
  category: Category[];
  readonly URL_API = '/api/category';

  constructor( private http: HttpClient) {
    this.selectedCategory = new Category();
   }

   postCategory(category: Category){
     return this.http.post(this.URL_API, category);
   }
   getCategory(){
     return this.http.get(this.URL_API);
   }
   putCategory(category: Category){
     return this.http.put(this.URL_API +`/ ${category.id_category}`, category);
   }

}
