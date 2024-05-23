import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ApiserviceService {

  constructor(private http:HttpClient) { }

  saveUserInfo(data:any)
  {
return this.http.post("http://localhost:8091/Auth/auth/userRegister",data,{responseType:"text"})
  }

  login(data:any)
  {
    return this.http.post("http://localhost:8091/Auth/auth/login",data,{responseType:"json"})
  }

  getotp(email:any,DOB:any)
  {
    return this.http.get("http://localhost:8091/Auth/auth/send-otp"+"/"+email+"/"+DOB,{responseType:"text"});
  }

  updatepasword(mail:any,pass:any,otp:any,date:any)
  {
    return this.http.get("http://localhost:8091/Auth/auth/forgot-password"+"/"+mail +"/"+pass+"/"+otp+"/"+date,{responseType:"text"});
  }

  sendEmailUrl(email:any,DOB:any)
  {
    return this.http.get("http://localhost:8091/Auth/auth/URLPassReset"+"/"+email+"/"+DOB,{responseType:"text"});
  }

  updatePassURl(email:any,DOB:any,pass:any)
  {
    return this.http.get("http://localhost:8091/Auth/auth/URL-forgot-password"+"/"+email+"/"+DOB+"/"+pass,{responseType:"text"});
   
  }

  getAllData()
  {
    return this.http.get("http://localhost:8092/Admin/admin/getUsers");
  }

  deleteStudent(id:any)
  {
    return this.http.get("http://localhost:8093/User/user/delete"+"/"+id,{responseType:"text"})
  }
}
