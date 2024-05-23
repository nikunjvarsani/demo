import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { ApiserviceService } from '../service/apiservice.service';
import { MatSort } from '@angular/material/sort';
import {MatTableModule,MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent {

  constructor(private apicall:ApiserviceService){
    this.getAllData()
  }
  datasource:any;
  datalist:any;
  displaycolumns:String[]=["id","price","City","branch","Country","Reponse"];
  @ViewChild(MatPaginator) pagi !:MatPaginator;
  @ViewChild(MatSort) sort !:MatSort;
  getAllData()
  {
    this.apicall.getAllData().subscribe(
      {
        next:(resp)=>{this.datalist=resp,
          console.log(resp)
          this.datasource=new MatTableDataSource(this.datalist);
          this.datasource.paginator=this.pagi;
          this.datasource.sort=this.sort;
       
        },
        error:(err)=>{console.log("error ",err);
        },
        complete:()=>{}
      
      }
    )
  }


  filter(f:any){
    let searchData=f.target.value;


    this.datasource.filter=searchData;
 

  }


  deleteByIdStudent(id:any){
    this.apicall.deleteStudent(id).subscribe(
      {
        next:(resp)=>{this.datalist=resp,
          console.log(resp)
        },
        error:(err)=>{console.log("error ",err);
        },
        complete:()=>{
          this.getAllData()
        }
      
      }
    )
  }
}
