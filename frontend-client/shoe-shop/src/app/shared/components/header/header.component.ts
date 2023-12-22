import { Component } from '@angular/core';
import { CartStore } from 'src/app/core/store/cart.store';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  cartItemCount$ = this.cartStore.select(state => state.count);

  constructor(private cartStore: CartStore) { }
}
