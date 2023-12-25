import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  loginWithPopup() {

    const width = 600;
    const height = 400;
    const left = (screen.width - width) / 2;
    const top = (screen.height - height) / 2;

    // Your backend endpoint that initiates the OAuth flow
    const url = `${environment.authService}/api/auth/login/google`;
    window.open(url, 'Auth', `width=${width},height=${height},top=${top},left=${left}`);
  }

  loginSamePage() {

    const url = `${environment.authService}/api/auth/login/google`;
    window.location.href = url;
  }
}
