import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';
import { AuthService } from 'src/app/core/service/AuthService';
import { CartStore } from 'src/app/core/store/cart.store';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  cartItemCount$ = this.cartStore.totalCount$;

  constructor(private cartStore: CartStore, private authService: AuthService) { }

  ngOnInit(): void {
  }

  loginWithPopup() {
    this.authService.loginWithPopup();
  }

  clearLocalStorage() {
    localStorage.clear();
    this.cartStore.setState({ cartItems: [] });
  }
}
