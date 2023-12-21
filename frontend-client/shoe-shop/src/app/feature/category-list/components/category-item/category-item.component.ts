import { Component, Input } from '@angular/core';
import { CategoryNode } from '../interfaces/CategoryNode';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-category-item',
  templateUrl: './category-item.component.html',
  styleUrls: ['./category-item.component.scss']
})
export class CategoryItemComponent {
  @Input() category!: CategoryNode;
  @Input() level: number = 0;

  constructor(private categoryService: CategoryService) { }

  get indentClass() {
    return `indent-${this.level}`;
  }

  click(categoryId: number) {
    console.log(categoryId + ' is clicked.');
  }
}
