import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CategoryStore } from '../../../../core/store/category.store';
import { tap } from 'rxjs';
import { CategoryNode } from '../interfaces/CategoryNode';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  categories$ = this.categoryStore.select(state => state.categories);

  constructor(private httpClient: HttpClient, private categoryStore: CategoryStore) { }

  ngOnInit(): void {

    this.httpClient.get(`${environment.productService}/api/categories/hierarchical`).subscribe((data) => {
      // assert data is CategoryNode[]
      const categoryNode: CategoryNode[] = (data as any)['data'];

      this.categoryStore.setCurrentCategory(categoryNode[0]);
      this.categoryStore.setCategories(categoryNode);

      // BFS to build categoryNodeIdMap
      const categoryNodeIdMap: Record<number, CategoryNode> = {};
      const queue = [categoryNode[0]];
      while (queue.length) {
        const node = queue.shift();
        if (!node) {
          continue;
        }
        categoryNodeIdMap[node.id] = node;
        if (node.subcategories) {
          queue.push(...node.subcategories);
        }
      }
      this.categoryStore.setCategoryIdMap(categoryNodeIdMap);
    })
  }
}
