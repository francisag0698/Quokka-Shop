import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  selectedProduct: Product;
  products: Product[];

  readonly URL_API = '/api/product';

  constructor(private http: HttpClient) {
    this.selectedProduct = new Product();
  }

  postProduct(product: Product) {
    return this.http.post(this.URL_API, product);
  }

  getProduct(){
    return this.http.get(this.URL_API);
  }

  putProduct(product: Product) {
    return this.http.put(this.URL_API+`/${product.id_product}`, product);
  }
}
