import { Component, OnInit } from '@angular/core';
import { CategoryStore } from 'src/app/core/store/category.store';
import { CategoryService } from 'src/app/feature/category-list/services/category.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private categoryStore: CategoryStore, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getProductByCategoryId(1).subscribe((products) => {
      this.categoryStore.setProducts(products);
    });
  }
}
