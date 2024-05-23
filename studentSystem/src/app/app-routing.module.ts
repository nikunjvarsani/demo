import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { ResetPassUrlComponent } from './reset-pass-url/reset-pass-url.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { authguardGuard } from './guard/authguard.guard';


const routes: Routes = [
  {path:'register',component:RegisterComponent,canActivate:[authguardGuard]},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomePageComponent},
  {path:'forgetpassword',component:ForgotpasswordComponent},
  {path:'update/:email/:dob',component:UpdatePasswordComponent},
  {path:'URLPassUpdate/:email/:dob',component:ResetPassUrlComponent},
  {path:'admin',component:AdminHomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
