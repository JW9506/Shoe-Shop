export interface DataResponse<T> {
  isSuccess: boolean;
  code: string;
  message: string;
  data: T;
}

export interface AuthResponse {
    access_token: string;
    expires_in: number;
    token_type: string;
    scope: string;
    id_token: string;
}

export interface AuthUser {
    at_hash: string;
    aud: string;
    azp: string;
    email: string;
    email_verified: boolean;
    exp: number;
    family_name: string;
    given_name: string;
    iat: number;
    iss: string;
    locale: string;
    name: string;
    picture: string;
    sub: string;
}