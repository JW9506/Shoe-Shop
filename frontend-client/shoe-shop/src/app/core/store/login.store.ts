import { Injectable } from '@angular/core';
import { ComponentStore } from '@ngrx/component-store';
import { AuthUser } from 'src/app/shared/interfaces';
import { TokenService } from '../service/TokenService';
import { tap } from 'rxjs';

export interface LoginState {
  user: AuthUser | null;
  jwtToken?: string;
}

@Injectable({ providedIn: 'root' })
export class LoginStore extends ComponentStore<LoginState> {

  private currentState: LoginState | null = null;

  constructor(private tokenService: TokenService) {
    super({ user: null, jwtToken: '' });
    this.state$.pipe(
      tap(state => this.currentState = state)
    ).subscribe();
    this.loadInitialState();
    this.effectOnStateChange();
  }

  getCurrentStateSync(): LoginState | null {
    return this.currentState;
  }

  // Method to load the initial state from localStorage
  private loadInitialState() {
    const jwt = sessionStorage.getItem('jwt');
    if (jwt) {
      this.setJwtToken(jwt);
      const user: AuthUser | null = this.tokenService.decodeToken(jwt);
      if (user) {
        this.setUser(user);
      }
    }
  }

  // Effect to subscribe to state changes and persist them to localStorage
  private effectOnStateChange() {
    this.state$.subscribe(state => {
      if (state.jwtToken) {
        sessionStorage.setItem('jwt', state.jwtToken);
      }
    });
  }

  readonly setUser = this.updater((state, user: AuthUser | null) => {
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
