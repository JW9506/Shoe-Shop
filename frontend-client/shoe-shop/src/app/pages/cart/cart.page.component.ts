import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { CartStore } from 'src/app/core/store/cart.store';
import { CartProduct } from 'src/app/feature/category-list/components/interfaces/CartProduct';

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart.page.component.html',
  styleUrls: ['./cart.page.component.scss']
})
export class CartPageComponent implements OnInit {

  cartItems$ = this.cartStore.select(s => s.cartItems);
  totalCount$ = this.cartStore.totalCount$;

  constructor(private cartStore: CartStore) {}

  ngOnInit(): void {
  }

  removeProduct(product: CartProduct) {
    this.cartStore.removeProductFromCart(product);
  }

  checkOut() {
    throw new Error('Method not implemented.');
  }
}
