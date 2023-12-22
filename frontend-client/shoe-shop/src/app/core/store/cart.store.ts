import { Injectable } from '@angular/core';
import { ComponentStore } from '@ngrx/component-store';
import { CartProduct } from 'src/app/feature/category-list/components/interfaces/CartProduct';
export interface CartState {
  cartItems: CartProduct[];
  count: number;
}

@Injectable({ providedIn: 'root' })
export class CartStore extends ComponentStore<CartState> {
  constructor() {
    super({ cartItems: [], count: 0 });
  }

  readonly addProductToCart = this.updater((state, item: CartProduct) => {
    const cartItems = [...state.cartItems];
    for (let cartItem of cartItems) {
      if (cartItem.id === item.id) {
        cartItem.quantity++;
        return {
          ...state,
          cartItems,
          count: state.count + 1
        }
      }
    }
    return {
      ...state,
      cartItems: cartItems.concat(item),
      count: state.count + 1
    }
  });

  readonly setCartItems = this.updater((state, CartItems: CartProduct[]) => ({
    ...state,
    CartItems
  }));

  readonly setCount = this.updater((state, count: number) => ({
    ...state,
    count
  }));
}
