import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Interceptor } from './core/network/Interceptor';
import { CategoryStore } from './core/store/category.store';
import { CategoryListModule } from './feature/category-list/category-list.module';
import { ProductListModule } from './feature/product-list/product-list.module';
import { LoadingComponent } from './shared/components/loading/loading.component';
import { CartPageComponent } from './pages/cart/cart.page.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './shared/components/header/header.component';
import { CartStore } from './core/store/cart.store';
import { LoginComponent } from './pages/login/login.component';
import { LoginStore } from './core/store/login.store';
import { AuthInterceptor } from './core/network/AuthInterceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoadingComponent,
    CartPageComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ProductListModule,
    CategoryListModule,
    BrowserAnimationsModule,
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: !isDevMode() }),
    StoreModule.forRoot({}, {})
  ],
  providers: [CategoryStore, CartStore, LoginStore, {
    provide: HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi: true
  }, {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
