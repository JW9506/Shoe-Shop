import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {
  }

  getProductByCategoryId(id: number) {
    if (id == null) {
      throw new Error('id is null');
    }
    this.httpClient.get(`${environment.productService}/api/categories/${id}/products`).subscribe((data) => {
      console.log(data);
    });
  }
}
