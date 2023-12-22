import { Component } from '@angular/core';
import { CartStore } from 'src/app/core/store/cart.store';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  cartItemCount$ = this.cartStore.select(state => state.count);

  constructor(private cartStore: CartStore) { }
}
