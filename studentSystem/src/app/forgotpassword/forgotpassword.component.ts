import { Component } from '@angular/core';
import { ApiserviceService } from '../service/apiservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent {

  bool=false;
  constructor(private apicall:ApiserviceService,private router:Router){}
  value:any;
  getEmail(email:any,DOB:any ){
   this.apicall.getotp(email,DOB).subscribe(
    {
      next:(resp)=>{
        this.value=resp;
      
      },
      error:(err)=>{console.log(err)
      },
      complete:()=>{
        if(this.value!="not valid email"){
        this.router.navigate(['update',email,DOB])
        
        }

        else{
          alert("Please Enter Valid Email")
        }
      }
      
    }
   )
  }

  
  sendUrl(email:any,DOB:any)
  {
    this.apicall.sendEmailUrl(email,DOB).subscribe(
      {
        next:(resp)=>{
          this.value=resp;
        
        },
        error:(err)=>{console.log(err)
        },
        complete:()=>{
          if(this.value!="invalid")
            {
              alert("URL Sent in this email "+email)
              this.router.navigateByUrl("home")
            }
            else{
              alert("Please Enter Valid Email")
            }
          
        }
        
      }
     )
  }
}
