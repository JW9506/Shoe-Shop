import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { of, tap } from 'rxjs';
import { CategoryStore } from 'src/app/core/store/category.store';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent {

  products$ = this.categoryStore.select(state => state.products);
  currentCategory$ = this.categoryStore.select(state => state.currentCategory);
  // products$ = of([
  //   {
  //     description: 'description',
  //     parentCategoryName: 'parentCategoryName',
  //     name: 'name',
  //     price: 123
  //   }
  // ])

  constructor(private httpClient: HttpClient, private categoryStore: CategoryStore) {
  }
}
