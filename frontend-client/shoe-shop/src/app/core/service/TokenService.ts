import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { AuthUser } from 'src/app/shared/interfaces';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  decodeToken(token: string): AuthUser | null {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }
}
