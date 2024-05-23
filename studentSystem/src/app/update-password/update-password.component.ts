import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ApiserviceService } from '../service/apiservice.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent {
  emailId:any;
  DoB:any;
  constructor(private route:ActivatedRoute,private Apicall:ApiserviceService){

    this.route.paramMap.subscribe(
      (dataa:ParamMap)=>{
            this.emailId=dataa.get('email')
            this.DoB=dataa.get('dob')
            
            
      }
    )
 
  }

  Value:any;
  verifyData(otp:any,pass:any)
{
  this.Apicall.updatepasword(this.emailId,pass,otp,this.DoB).subscribe(
    {
      next:(resp)=>{this.Value=resp},
      error:(err)=>{console.log(err)
      },
      complete:()=>{
        console.log("vallue",this.Value)
        if(this.Value=="Password Not update")
          { alert("Please Enter Valid OTP And DOB ")
            
          }
          else if(this.Value=="Password update"){
            alert("Password Update Successfully !!!")
          }
          else{
            alert("Your OTP Expire")
          }
      }
    }
  )
}
  

}
