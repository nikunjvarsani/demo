import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class StudentinterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
   
    let myToken=localStorage.getItem("JWTTOKEN")
    
    
    request=request.clone({
      headers:request.headers.set('token',"ABC "+myToken)
    })
    return next.handle(request);
  }
}
