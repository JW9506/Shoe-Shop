import { Component, OnInit } from '@angular/core';
import { ProductService } from './feature/product-list/services/product.service';
import { CartStore } from './core/store/cart.store';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'shoe-shop';

  constructor() { }

  ngOnInit(): void {
  }
}
