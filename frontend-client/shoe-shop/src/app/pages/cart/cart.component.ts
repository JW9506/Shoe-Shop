import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';
import { CartStore } from 'src/app/core/store/cart.store';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  cartItems$ = this.cartStore.select(s => s.cartItems);
  count$ = this.cartStore.select(s => s.count);

  constructor(private cartStore: CartStore) {}

  ngOnInit(): void {
  }
}
