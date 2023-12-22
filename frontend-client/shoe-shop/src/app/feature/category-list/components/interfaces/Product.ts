export interface Product {
  description: string,
  parentCategoryName: string;
  name: string;
  originalPrice: number;
  salePrice: number | null;
  rating: number;
  inStock: boolean;
  brand: string;
  tags: string[];
  imageUrl: string;
}
