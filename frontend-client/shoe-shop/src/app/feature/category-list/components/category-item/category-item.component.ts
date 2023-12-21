import { Component, Input } from '@angular/core';
import { CategoryNode } from '../interfaces/CategoryNode';

@Component({
  selector: 'app-category-item',
  templateUrl: './category-item.component.html',
  styleUrls: ['./category-item.component.scss']
})
export class CategoryItemComponent {
  @Input() category!: CategoryNode;
  @Input() level: number = 0;

  get indentClass() {
    return `indent-${this.level}`;
  }
}
