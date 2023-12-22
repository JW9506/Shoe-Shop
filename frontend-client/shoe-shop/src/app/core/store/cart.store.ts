import { Injectable } from '@angular/core';
import { ComponentStore } from '@ngrx/component-store';
import { Product } from '../../feature/category-list/components/interfaces/Product';

export interface CartState {
  cartItems: Product[];
  count: number;
}

@Injectable({ providedIn: 'root' })
export class CartStore extends ComponentStore<CartState> {
  constructor() {
    super({ cartItems: [], count: 0 });
  }

  readonly addProductToCart = this.updater((state, items: Product) => {
    const cartItems = state.cartItems.concat(items);
    return {
      ...state,
      cartItems,
      count: state.count + 1
    }
  });

  readonly setCartItems = this.updater((state, CartItems: Product[]) => ({
    ...state,
    CartItems
  }));

  readonly setCount = this.updater((state, count: number) => ({
    ...state,
    count
  }));
}
