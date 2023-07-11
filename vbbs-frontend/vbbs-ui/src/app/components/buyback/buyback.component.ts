import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, Observable, ReplaySubject, Subject } from 'rxjs';
import { AuthserviceService } from 'src/app/services/authservice.service';

import { VbbsServiceService } from '../../services/vbbs-service.service';

@Component({
  selector: 'app-buyback',
  templateUrl: './buyback.component.html',
  styleUrls: ['./buyback.component.css']
})

export class BuybackComponent implements OnInit {
  isEditable = false;
  vehicleInfo: any;
  updateCustomerDetails: any = {};
  arbitrationToggle = true;
  offsetToggle = true;
  status = 'Enable';
  showOffsetDetails = true;
  isDisabled = true;

  showBuyBackDetail = false;
  showBuybackReason = false;
  showBuybackCategory = false;
  showAttachement = false;

  enableCancelAndSubmitForApproval = false;
  enableSubmitAndApprove = false;


  VehicleForm = this._formBuilder.group({
    VIN: ['', Validators.required],
  });

  vehicleDetails = this._formBuilder.group({
    vinNumber: [''],
    modelName: [''],
    make: [''],
    modelYear: [''],
    currentMileage: ['']
  })


  customerDetails = this._formBuilder.group({
    firstName: [''],
    lastName: [''],
    emailId: ['', Validators.required],
    bestTimeToCall: ['', Validators.required],
    phone1: ['', Validators.required],
    phone2: ['', Validators.required],
    phone3: ['', Validators.required],
    contactPrefernce: [''],
    rental: ['', Validators.required]
  })


  constructor(private _formBuilder: FormBuilder,
    private service: VbbsServiceService, private cookies: CookieService, private router: Router, private auth: AuthserviceService) { }

  ngOnInit(): void {
    const jwtToken = this.cookies.get('jwt_token');
    if (!jwtToken) {
      this.router.navigate(['login']);
    }
  }

  toggleCancelAndSubmitForApproval(value: any){
    this.enableCancelAndSubmitForApproval = value;
  }

  toggleSubmitAndApprove(value: any){
    this.enableSubmitAndApprove = value;
  }

  toggleArbitration() {
    this.arbitrationToggle = !this.arbitrationToggle;
  }

  toggleOffset() {
    this.offsetToggle = !this.offsetToggle;
    if (this.showOffsetDetails) {
      this.showOffsetDetails = false;
    } else {
      this.showOffsetDetails = true;
    }
  }

  resetSearch(value: any){
  (<HTMLInputElement>document.getElementById("vin")).value = value;
  }

  toggleBuybackReason(value: any){
    this.showBuybackReason = value;
    console.log(this.showBuybackReason + "------reason access-----")
  }

  toggleBuybackCategory(value: any){
    this.showBuybackCategory = value;
    console.log(this.showBuybackCategory + "------category access-----")
  }

  toggleAttachment(value: any){
    this.showAttachement = value;
    console.log(this.showAttachement + "------attachment access-----")
  }

  onchangeYes() {
    document.getElementById('yes')?.classList.add("search-symbol");
    document.getElementById('no')?.classList.remove("search-symbol");
  }

  onchangeNo() {
    document.getElementById('yes')?.classList.remove("search-symbol");
    document.getElementById('no')?.classList.add("search-symbol");
  }


  getVehicleDetails() {
    localStorage.removeItem("vehicleDetails");
    this.vehicleDetails.reset();
    this.customerDetails.reset();
    this.service.getVehicleByVin(this.VehicleForm.value.VIN).subscribe((data) => {
      this.vehicleInfo = data;
      console.log(this.vehicleInfo)
      localStorage.setItem("vehicleDetails", JSON.stringify(this.vehicleInfo));
      this.vehicleDetails = this._formBuilder.group({
        vinNumber: [this.vehicleInfo.vin],
        modelName: [this.vehicleInfo.modelName],
        make: [this.vehicleInfo.make],
        modelYear: [this.vehicleInfo.modelYear],
        currentMileage: [this.vehicleInfo.currentMileage]
      })
      this.customerDetails = this._formBuilder.group({
        firstName: [this.vehicleInfo.customer.firstName],
        lastName: [this.vehicleInfo.customer.lastName],
        emailId: [this.vehicleInfo.customer.emailId, Validators.required],
        bestTimeToCall: [this.vehicleInfo.customer.bestTimeToCall, Validators.required],
        phone1: [this.vehicleInfo.customer.phone1, Validators.required],
        phone2: [this.vehicleInfo.customer.phone2],
        phone3: [this.vehicleInfo.customer.phone3],
        contactPrefernce: [this.vehicleInfo.customer.contactPreference],
        rental: [this.vehicleInfo.customer.rental]

      })
      this.arbitrationToggle = this.vehicleInfo.customer.rental
      this.isDisabled = false
    },
      (error) => {                              //Error callback
        if (error.status == 404) {
          alert(error.error.errorMessage)
        };
      })

  }


  section1() {

    this.updateCustomerDetails = {
      customerId: this.vehicleInfo.customer.customerId,
      firstName: this.customerDetails.value.firstName,
      lastName: this.customerDetails.value.lastName,
      emailId: this.customerDetails.value.emailId,
      bestTimeToCall: this.customerDetails.value.bestTimeToCall,
      phone1: this.customerDetails.value.phone1,
      phone2: this.customerDetails.value.phone2,
      phone3: this.customerDetails.value.phone3,
      contactPreference: this.customerDetails.value.contactPrefernce,
      rental: this.customerDetails.value.rental,
      middleInitial: null
    }
    const shallowComparison =
      Object.keys(this.vehicleInfo.customer).length === Object.keys(this.updateCustomerDetails).length &&
      (Object.keys(this.vehicleInfo.customer) as (keyof typeof this.vehicleInfo.customer)[]).every((key) => {
        return (
          Object.prototype.hasOwnProperty.call(this.updateCustomerDetails, key) && this.vehicleInfo.customer[key] === this.updateCustomerDetails[key]
        );
      });
    console.log(shallowComparison)
    if (!shallowComparison) {
      this.service.updateCustomer(this.updateCustomerDetails).subscribe(data => {
        console.log(data);
      })
    }

    this.showBuyBackDetail = true;

  }
}