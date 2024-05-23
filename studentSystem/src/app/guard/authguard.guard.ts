import { Inject, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { ApiserviceService } from '../service/apiservice.service';

export const authguardGuard: CanActivateFn = (route, state) => {
  let router=inject(Router);
  let apicall=inject(ApiserviceService);
  let islogin=localStorage.getItem("JWTTOKEN")

  if(!islogin){
    
    
    router.navigateByUrl('login');
    
    return false
  }
  return true;
};
