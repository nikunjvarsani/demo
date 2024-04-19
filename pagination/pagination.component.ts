import { Component, ViewChild } from '@angular/core';
import { ApiserviceService } from '../service/apiservice.service';
import { MatPaginator } from '@angular/material/paginator';

import {MatTableModule,MatTableDataSource} from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent {

  constructor(private apicall:ApiserviceService){
    this.getAllData()
  }
  datasource:any;
  datalist:any;
  displaycolumns:String[]=["id","name","price","City"];
  @ViewChild(MatPaginator) pagi !:MatPaginator;
  @ViewChild(MatSort) sort !:MatSort;
  getAllData()
  {
    this.apicall.getAllData().subscribe(
      {
        next:(resp)=>{this.datalist=resp,
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
console.log("search data is "+searchData);

    this.datasource.filter=searchData;
    console.log("search data is=== "+this.datasource);

  }
}
