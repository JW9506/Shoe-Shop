import { Component, Input } from '@angular/core';
import { Product } from 'src/app/feature/category-list/components/interfaces/Product';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss']
})
export class ProductItemComponent {
  @Input() product!: Product;
}
