import { Component, Input } from '@angular/core';
import { CartStore } from 'src/app/core/store/cart.store';
import { CartProduct } from 'src/app/feature/category-list/components/interfaces/CartProduct';
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
    const productItem: CartProduct = { ...p, quantity: 0 };
    this.cartStore.addProductToCart(productItem);
  }
}
