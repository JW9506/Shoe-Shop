export interface Product {
  description: string,
  parentCategoryName: string;
  name: string;
  originalPrice: number;
  salePrice: number | null;
  rating: number;
  isInStock: boolean;
  brand: string;
  tags: string[];
  imageUrl: string;
}
