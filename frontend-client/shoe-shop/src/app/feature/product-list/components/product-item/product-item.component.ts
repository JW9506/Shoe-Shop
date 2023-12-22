import { Component, Input } from '@angular/core';
import { CartStore } from 'src/app/core/store/cart.store';
import { Product } from 'src/app/feature/category-list/components/interfaces/Product';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss']
})
export class ProductItemComponent {

  @Input() product!: Product;

  constructor(private cartStore: CartStore) { }

  addProductToCart(p: Product) {
    this.cartStore.addProductToCart(p);
  }
}
