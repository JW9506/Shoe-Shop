import { Component, Input } from '@angular/core';
import { CategoryNode } from '../interfaces/CategoryNode';
import { CategoryService } from '../../services/category.service';
import { CategoryStore } from '../../../../core/store/category.store';
import { tap } from 'rxjs';

@Component({
  selector: 'app-category-item',
  templateUrl: './category-item.component.html',
  styleUrls: ['./category-item.component.scss']
})
export class CategoryItemComponent {
  @Input() category!: CategoryNode;
  @Input() level: number = 0;

  constructor(private categoryService: CategoryService, public categoryStore: CategoryStore) { }

  click(categoryId: number) {
    this.categoryService.getProductByCategoryId(categoryId).subscribe((data) => {
      this.categoryStore.setProducts(data);
    })
  }
}
