export interface DataResponse<T> {
  isSuccess: boolean;
  code: string;
  message: string;
  data: T;
}
