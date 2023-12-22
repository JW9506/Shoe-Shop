import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';
import { CartStore } from 'src/app/core/store/cart.store';

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart.page.component.html',
  styleUrls: ['./cart.page.component.scss']
})
export class CartPageComponent implements OnInit {

  cartItems$ = this.cartStore.select(s => s.cartItems);
  count$ = this.cartStore.select(s => s.count);

  constructor(private cartStore: CartStore) {}

  ngOnInit(): void {
  }
}
