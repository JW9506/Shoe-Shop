import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay, finalize, switchMap } from 'rxjs/operators';
import { LoadingService } from '../service/LoadingService';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private loadingService: LoadingService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loadingService.showLoading();

    return of(null).pipe(
      delay(500),
      switchMap(() => next.handle(req)),
      finalize(() => this.loadingService.hideLoading())
    );
  }
}
