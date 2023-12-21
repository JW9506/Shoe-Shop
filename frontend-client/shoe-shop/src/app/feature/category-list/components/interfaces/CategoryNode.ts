export interface CategoryNode {
    id: number,
    name: string,
    subcategories: CategoryNode[]
}