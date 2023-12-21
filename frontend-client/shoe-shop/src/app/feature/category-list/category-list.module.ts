import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { CategoryListRoutingModule } from './category-list-routing.module';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryItemComponent } from './components/category-item/category-item.component';


@NgModule({
  declarations: [CategoryListComponent, CategoryItemComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    CategoryListRoutingModule
  ],
  exports: [
    CategoryListComponent
  ]
})
export class CategoryListModule { }
