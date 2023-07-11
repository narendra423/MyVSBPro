import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { EmailValidator, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { User } from 'src/app/interface/user';
import { AuthserviceService } from 'src/app/services/authservice.service';
import { VbbsServiceService } from 'src/app/services/vbbs-service.service';
import { Role } from '../../interface/role';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  cookiesData: any;
  errorMessage: string = "";
  users: User;
  timer: any;
  isLoading!: boolean;
  notFound: boolean = false;
  listOfusers : any;
  // roles: Role[] = [
  //   {value: '', viewValue: 'Dealer'},
  //   {value: '', viewValue: 'Manufacture Agent'}
  // ];
  constructor( private router: Router, private fb: FormBuilder, private service: VbbsServiceService, private cookies:CookieService, private http:HttpClient, private auth:AuthserviceService) { }

  ngOnInit(): void {
    const jwtToken = this.cookies.get('jwt_token');
    console.log(jwtToken)
    if(jwtToken){
      this.router.navigate(['']);
    }
  }

  userloginForm: FormGroup = this.fb.group({
    emailId: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],

  })

  onSubmit() {
    //const requestBody = { userName: this.users.emailId, password: this.users.password }\

    this.service.checkBranchAdmin(this.userloginForm.value.emailId, this.userloginForm.value.password).subscribe((data: any) => {
      console.log(data)
      if (data == null) {

      } else {
        console.log(this.userloginForm.value);

        this.auth.generateToken(this.userloginForm.value).subscribe((data) => {
          const parsedData = JSON.parse(data)
          this.cookies.set('jwt_token', parsedData.JWT, { expires: 30 })
          console.log(parsedData)
          this.router.navigate(['buyback']);
        });
      }
    }, (error: any) => {
      // console.log(error.error.errorMessage);
      this.errorMessage = error.error.errorMessage;
      this.setPostData(this.errorMessage);
      this.timer = setTimeout(this.toggleLoading.bind(this), 2000);
    });
  }

  toggleLoading() {
    this.isLoading = !this.isLoading;
  }
  setPostData(data: any) {
    this.errorMessage = data;
    if (this.timer) {
      clearTimeout(this.timer);
    }
    this.isLoading = false;
  }
}
