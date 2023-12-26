import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';
import { AuthService } from 'src/app/core/service/AuthService';
import { CartStore } from 'src/app/core/store/cart.store';
import { LoginStore } from 'src/app/core/store/login.store';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  cartItemCount$ = this.cartStore.totalCount$;
  user$ = this.loginStore.select(state => state.user);

  constructor(private loginStore: LoginStore, private cartStore: CartStore, private authService: AuthService) { }

  ngOnInit(): void {
  }

  login() {
    this.authService.loginSamePage();
  }

  clearLocalStorage() {
    this.cartStore.setState({ cartItems: [] });
    this.loginStore.setState({ user: null, jwtToken: '' });
    sessionStorage.clear();
    localStorage.clear();
  }
}
