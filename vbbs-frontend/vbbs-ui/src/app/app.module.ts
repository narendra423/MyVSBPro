import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialExampleModule } from 'src/material.module';
import { BuybackComponent } from './components/buyback/buyback.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { HttpClientModule } from '@angular/common/http';

import { BuybackdetailsComponent } from './components/buybackdetails/buybackdetails.component';
import { BuybackcategoryComponent } from './components/buybackcategory/buybackcategory.component';
import { BuybackreasonComponent } from './components/buybackreason/buybackreason.component';
import { HeaderComponent } from './components/header/header.component';
import { AttachmentComponent } from './components/attachment/attachment.component';

@NgModule({
  declarations: [
    AppComponent,
    BuybackComponent,
    BuybackcategoryComponent,
    BuybackreasonComponent,
    LoginComponent,
    SignUpComponent,
    BuybackdetailsComponent,
    HeaderComponent,
    AttachmentComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialExampleModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
