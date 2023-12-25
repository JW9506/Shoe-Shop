import { Injectable } from '@angular/core';
import { ComponentStore } from '@ngrx/component-store';
import { AuthUser } from 'src/app/shared/interfaces';

export interface LoginState {
  user?: AuthUser;
  jwtToken?: string;
}

@Injectable({ providedIn: 'root' })
export class LoginStore extends ComponentStore<LoginState> {

  constructor() {
    super({ user: undefined, jwtToken: '' });
  }

  readonly setUser = this.updater((state, user: AuthUser) => {
    return {
      ...state,
      user,
    }
  });

  readonly setJwtToken = this.updater((state, token: string) => {
    return {
      ...state,
      jwtToken: token,
    }
  });
}
