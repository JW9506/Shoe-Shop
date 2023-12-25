import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginStore } from 'src/app/core/store/login.store';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  // obtain code
  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private loginStore: LoginStore) {

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const code = params['code'];
      this.httpClient.get(`http://localhost:8092/api/auth/login/google/callback?code=${code}`).subscribe((data) => {
        console.log(data);
      });
    });
  }
}
