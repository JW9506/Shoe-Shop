import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ProductListRoutingModule } from './product-list-routing.module';
import { ProductListComponent } from './components/product-list/product-list.component';


@NgModule({
  declarations: [
    ProductListComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ProductListRoutingModule
  ],
  exports: [
    ProductListComponent
  ]
})
export class ProductListModule { }
