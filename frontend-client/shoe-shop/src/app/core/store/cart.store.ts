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
    this.loadInitialState();
    this.effectOnStateChange();
  }

  // Method to load the initial state from localStorage
  private loadInitialState() {
    const savedCartState = localStorage.getItem('cartState');
    if (savedCartState) {
      this.setState(JSON.parse(savedCartState));
    }
  }

  // Effect to subscribe to state changes and persist them to localStorage
  private effectOnStateChange() {
    this.state$.subscribe(state => {
      localStorage.setItem('cartState', JSON.stringify(state));
    });
  }

  private totalPriceCalculator(cartItem: CartProduct) {
    return parseFloat((cartItem.quantity * (cartItem.salePrice || cartItem.originalPrice)).toFixed(2));
  }

  readonly addProductToCart = this.updater((state, item: CartProduct) => {
    const cartItems = [...state.cartItems];
    for (let cartItem of cartItems) {
      if (cartItem.id === item.id) {
        ++cartItem.quantity;
        cartItem.totalPrice = this.totalPriceCalculator(cartItem);
        return {
          ...state,
          cartItems,
          count: state.count + 1
        }
      }
    }
    ++item.quantity;
    item.totalPrice = this.totalPriceCalculator(item);
    return {
      ...state,
      cartItems: cartItems.concat(item),
      count: state.count + 1
    }
  });

  readonly removeProductFromCart = this.updater((state, product: CartProduct) => {
    const cartItems = state.cartItems.filter(item => item.id !== product.id);
    return {
      ...state,
      cartItems,
      count: state.count - product.quantity
    }
  })

  readonly setCartItems = this.updater((state, CartItems: CartProduct[]) => ({
    ...state,
    CartItems
  }));

  readonly setCount = this.updater((state, count: number) => ({
    ...state,
    count
  }));
}
