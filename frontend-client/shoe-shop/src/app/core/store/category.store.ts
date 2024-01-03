import { Injectable } from '@angular/core';
import { ComponentStore } from '@ngrx/component-store';
import { CategoryNode } from '../../feature/category-list/components/interfaces/CategoryNode';
import { Product } from '../../feature/category-list/components/interfaces/Product';

export interface CategoryState {
  categories: CategoryNode[];
  products: Product[];
  categoryIdMap: Record<number, CategoryNode>;
  currentCategory: CategoryNode | null;
}

@Injectable({ providedIn: 'root' })
export class CategoryStore extends ComponentStore<CategoryState> {
  constructor() {
    super({ categories: [], products: [], categoryIdMap: {}, currentCategory: null });
  }

  readonly setCategoryIdMap = this.updater((state, categoryIdMap: Record<number, CategoryNode>) => (
    {
      ...state,
      categoryIdMap
    }
  ));

  readonly setCurrentCategory = this.updater((state, currentCategory: CategoryNode) => ({
    ...state,
    currentCategory
  }));

  readonly setCategories = this.updater((state, categories: CategoryNode[]) => ({
    ...state,
    categories
  }));

  readonly setProducts = this.updater((state, products: Product[]) => ({
    ...state,
    products
  }));
}
