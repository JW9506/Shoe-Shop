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

  constructor(private httpClient: HttpClient) { }

  getProductByCategoryId(categoryId: number)  {
    return this.httpClient.get<DataResponse<Product[]>>(`${environment.productService}/api/categories/${categoryId}/products`).pipe(map((data) => data.data));
  }
}
