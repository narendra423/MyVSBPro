import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { BuybackComponent } from './components/buyback/buyback.component';
import { BuybackreasonComponent } from './components/buybackreason/buybackreason.component';
const routes: Routes = [
  { path: '', redirectTo: 'buyback', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
  {path:"buyback",component:BuybackComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
