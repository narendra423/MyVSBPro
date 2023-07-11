import { Component, OnInit } from '@angular/core';
import { EmailValidator, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Role } from 'src/app/interface/role';
import { User } from 'src/app/interface/user';
import { VbbsServiceService } from 'src/app/services/vbbs-service.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
 
  newUserObject:any;
  user: any;
  roles: Role[] = [
    {roleId: 1, userRole: 'INITIATER'},
    {roleId: 4, userRole: 'APPROVER'}
  ];

  constructor(private fb: FormBuilder, private vbbsVervice: VbbsServiceService ) { }

  ngOnInit(): void {
    this.onSubmit
  }

  registrationForm: FormGroup = this.fb.group({
    firstName: ["", [Validators.required]],
    lastName:["", [Validators.required]],
    middleInitial:["",[Validators.required]],
    emailId:["",[Validators.required,Validators.email]],
    password:["", [Validators.required, Validators.minLength(8)]],
    roles: ["",[Validators.required]]
   
  });
  
onSubmit() {
console.log(this.registrationForm.value.roles)
  this.newUserObject = {
    emailId: this.registrationForm.value.emailId,
    firstName: this.registrationForm.value.firstName,
    lastName: this.registrationForm.value.lastName,
    middleInitial: this.registrationForm.value.middleInitial,
    password: this.registrationForm.value.password,
    role: {
      roleId: this.registrationForm.value.roles
      
    },
    userId: 0,
  }
  this.vbbsVervice.createStudent(this.newUserObject).subscribe(data => {
    this.user = data;
    alert("Student Created Successfully");
  })
}

}