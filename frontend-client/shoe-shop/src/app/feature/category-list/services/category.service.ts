import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../components/interfaces/Product';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs';
import { DataResponse } from 'src/app/shared/interfaces';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  getProductByCategoryId(categoryId: number)  {
    console.log('getProductByCategoryId', categoryId);
    return this.httpClient.get<DataResponse<Product[]>>(`${environment.productService}/api/categories/${categoryId}/products`).pipe(map((data) => data.data));
  }

  constructor(private httpClient: HttpClient) {
  }
}
