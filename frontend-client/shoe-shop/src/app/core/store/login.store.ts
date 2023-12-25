import { Injectable } from '@angular/core';
import { ComponentStore } from '@ngrx/component-store';

export interface LoginState {
  userName: string;
  email: string;
}

@Injectable({ providedIn: 'root' })
export class LoginStore extends ComponentStore<LoginState> {

  constructor() {
    super({ userName: '', email: '' });
  }

  readonly setUserName = this.updater((state, newState: LoginState) => {
    return {
      ...state,
      userName: newState.userName,
    }
  });

  readonly setEmail = this.updater((state, newState: LoginState) => {
    return {
      ...state,
      email: newState.email,
    }
  });
}
