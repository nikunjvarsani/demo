import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ApiserviceService } from './service/apiservice.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { ResetPassUrlComponent } from './reset-pass-url/reset-pass-url.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { StudentinterceptorInterceptor } from './interceptor/studentinterceptor.interceptor';




@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    ForgotpasswordComponent,
    UpdatePasswordComponent,
    ResetPassUrlComponent,
    HomePageComponent,
    AdminHomePageComponent,
   

   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    
    
    
  ],
  providers: [ApiserviceService,{provide:HTTP_INTERCEPTORS,useClass:StudentinterceptorInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
