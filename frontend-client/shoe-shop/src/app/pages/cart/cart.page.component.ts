import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { CartStore } from 'src/app/core/store/cart.store';
import { LoginStore } from 'src/app/core/store/login.store';
import { CartProduct } from 'src/app/feature/category-list/components/interfaces/CartProduct';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart.page.component.html',
  styleUrls: ['./cart.page.component.scss']
})
export class CartPageComponent implements OnInit {

  cartItems$ = this.cartStore.select(s => s.cartItems);
  totalCount$ = this.cartStore.totalCount$;

  constructor(private cartStore: CartStore, private httpClient: HttpClient, private loginStore: LoginStore) {}

  ngOnInit(): void {
  }

  removeProduct(product: CartProduct) {
    this.cartStore.removeProductFromCart(product);
  }

  checkOut() {
    const customerId = this.loginStore.getCurrentStateSync()?.user?.email;
    if (customerId == null) {
      alert("Please login to checkout");
      return;
    }
    this.cartStore.select(s => s.cartItems).subscribe((cartItems) => {
      const cartItemDtos = [];
      for (let item of cartItems) {
        const cartItemDto = {
          productId: item.id,
          quantity: item.quantity,
          totalPrice: item.totalPrice,
        }
        cartItemDtos.push(cartItemDto);
      }
      this.httpClient.post(`${environment.orderService}/api/order/checkout`, {
        customerId,
        cartItemDtos,
      }).subscribe((data) => {
        console.log(data);
      });
    });
  }
}
