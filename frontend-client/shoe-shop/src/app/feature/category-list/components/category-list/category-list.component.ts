import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CategoryNode } from '../interfaces/CategoryNode';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  categories: CategoryNode[] = []

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get(`${environment.productService}/api/categories/hierarchical`).subscribe((data) => {
      this.categories = (data as any)['data']
    })
  }
}
