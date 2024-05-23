import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ApiserviceService } from '../service/apiservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit  {
LogData:any;
constructor(private fb:FormBuilder,private apicall:ApiserviceService,private router:Router){
  this.LogData=this.fb.group({
    name:[],
   
    password:[],
   
  })
}
  ngOnInit(): void {
   
  }
LoginData(){
  
  
  this.apicall.login(this.LogData.value).subscribe(
    {
      next:(resp:any)=>{
        console.log(resp);
        
        localStorage.setItem("JWTTOKEN",resp.jwtToken)
      },
      error:(err)=>{console.log(err)
        alert("Please Enter Valid Username and Password");
      },
      complete:()=>{
        this.router.navigateByUrl("admin");
      }
    }
  )
}

get getControls()
{
  return this.LogData.controls;
}
}
