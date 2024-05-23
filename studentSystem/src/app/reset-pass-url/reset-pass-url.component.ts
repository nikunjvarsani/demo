import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ApiserviceService } from '../service/apiservice.service';

@Component({
  selector: 'app-reset-pass-url',
  templateUrl: './reset-pass-url.component.html',
  styleUrls: ['./reset-pass-url.component.css']
})
export class ResetPassUrlComponent {
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
  verifyURLData(pass:any)
{
  this.Apicall.updatePassURl(this.emailId,this.DoB,pass).subscribe(
    {
      next:(resp)=>{this.Value=resp},
      error:(err)=>{console.log(err)
      },
      complete:()=>{
        console.log("vallue",this.Value)
        if(this.Value!="not save")
          { 
            alert("Password Update Successfully !!!")
          }
          else{
            alert("Password Update Not !!!")
          }
         
      }
    }
  )
}

}
