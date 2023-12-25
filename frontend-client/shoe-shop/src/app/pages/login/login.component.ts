import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from 'src/app/core/service/TokenService';
import { LoginStore } from 'src/app/core/store/login.store';
import { AuthResponse, AuthUser, DataResponse } from 'src/app/shared/interfaces';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private loginStore: LoginStore, private tokenService: TokenService, private router: Router) {

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const code = params['code'];
      this.httpClient.get<DataResponse<AuthResponse>>(`${environment.authService}/api/auth/login/google/callback?code=${code}`).subscribe((data) => {
        const jwtToken = data.data.id_token;
        const user: AuthUser | null = this.tokenService.decodeToken(jwtToken);
        if (user) {
          this.loginStore.setJwtToken(jwtToken);
          this.loginStore.setUser(user);
          this.router.navigate(['/']);
        }
      });
    });
  }
}
