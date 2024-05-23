import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiserviceService } from '../service/apiservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
regData:any;
newData:any;
constructor(private fb:FormBuilder,private apicall:ApiserviceService,private router:Router){
  this.regData=this.fb.group({
    name:[],
    email:['',[Validators.required,Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]],
    password:[],
    dob:[],mobile:[],address:[]
  })
  this.newData.fb.group({
    
  })

}

Registerdata(){
  
this.apicall.saveUserInfo(this.regData.value).subscribe(
  {
    next:(resp)=>{console.log(resp)},
    error:(err)=>{console.log(err)
    },
    complete:()=>{
      alert("Register Successfully")
      this.router.navigateByUrl('login')}
  }
)
 
}

get getControls()
{
  return this.regData.controls;
}
}
