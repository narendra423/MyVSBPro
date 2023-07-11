import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { VbbsServiceService } from 'src/app/services/vbbs-service.service';

@Component({
  selector: 'app-buybackreason',
  templateUrl: './buybackreason.component.html',
  styleUrls: ['./buybackreason.component.css']
})
export class BuybackreasonComponent implements OnInit {
  buybackRepairedToggle = true;
  physicaldamage = true;
  vehicleindealer = true;
  newBuybackReasonForm: any;
  newDealerForm: any;
  showHideRepairedReason:any;
  @Input() buybackFromDB:any;
  buybackReasonTemp: any;
  dealerDetails:any;
  showDealerDetails= false;

  @Output() showBuybackCategory: EventEmitter<Boolean> = new EventEmitter();

  constructor(private service:VbbsServiceService, private fb: FormBuilder) { }

  buybackReasonForm = this.fb.group({
    nonConfirmity: ['', Validators.required],
    isBuybackReasonRepaired: ['false', Validators.required],
    reasonForNotRepaired: ['', Validators.required],
    anyPhysicalDamage: [false, Validators.required],
    vehicleInDealership: [true, Validators.required],
    justification: ['', Validators.required],
    additionalInfo: ['', Validators.required]
  })

  dealerDetailsForm = this.fb.group({
    dealerId:['',Validators.required],
    code: ['', Validators.required],
    dealershipName: ['', Validators.required],
    emailId: ['', Validators.required],
    contact: ['', Validators.required],
  })
  

  ngOnInit(): void {
    const data = JSON.parse(localStorage.getItem('vehicleDetails') || '{}');
    this.service.getVehicleByVin(data.vin).subscribe(result => {
      localStorage.setItem('buybackDetails', JSON.stringify(result.buyback));
    });
  
    if(this.buybackFromDB.buybackReason != null){
       this.buybackReasonTemp = this.buybackFromDB.buybackReason;
       this.buybackRepairedToggle=!this.buybackReasonTemp.isBuybackReasonRepaired;
       this.physicaldamage=!this.buybackReasonTemp.anyPhysicalDamage;
       this.vehicleindealer=!this.buybackReasonTemp.vehicleInDealership;
      console.log(this.buybackReasonTemp)
      this.buybackReasonForm = this.fb.group({
        nonConfirmity: [this.buybackReasonTemp.nonConfirmity,[ Validators.required]],
        isBuybackReasonRepaired: [this.buybackReasonTemp.isBuybackReasonRepaired, [ Validators.required]],
        reasonForNotRepaired: [this.buybackReasonTemp.reasonForNotRepaired,[ Validators.required]],
        anyPhysicalDamage: [this.buybackReasonTemp.anyPhysicalDamage, [ Validators.required]],
        vehicleInDealership: [this.buybackReasonTemp.vehicleInDealership, [ Validators.required]],
        justification: [this.buybackReasonTemp.justification, [ Validators.required]],
        additionalInfo: [this.buybackReasonTemp.additionalInfo, [ Validators.required]]
      })

      this.showDealerDetails=this.buybackFromDB.buybackReason.vehicleInDealership;
    }

    this.showHideRepairedReason=this.buybackReasonForm.value.isBuybackReasonRepaired;
  }

  getDealerDetails(){
    this.service.getDealerDetailsbyCode(this.dealerDetailsForm.value.code).subscribe((data)=>{
      this.dealerDetails=data;
      this.dealerDetailsForm=this.fb.group({
        dealerId:[this.dealerDetails.dealerId],
        code: [this.dealerDetails.code],
        dealershipName: [this.dealerDetails.dealershipName],
        emailId: [this.dealerDetails.emailId],
        contact: [this.dealerDetails.contact]
      })
      console.log(this.dealerDetailsForm.value)
    })

  }
  
  toggleBuybackRepaired() {
    this.buybackRepairedToggle = !this.buybackRepairedToggle;
    this.showHideRepairedReason=!this.showHideRepairedReason;
  }
  togglePhysicalDamage() {
    this.physicaldamage = !this.physicaldamage;
  }
  toggleVehicleInDealer() {
    this.vehicleindealer = !this.vehicleindealer;
    this.showDealerDetails=!this.showDealerDetails;
  }
  buybackReasonFormSubmit() {
    
    if(this.buybackFromDB.buybackReason==null){
      //create buyback reason
      this.newBuybackReasonForm={
        nonConfirmity:this.buybackReasonForm.value.nonConfirmity,
        isBuybackReasonRepaired: this.buybackReasonForm.value.isBuybackReasonRepaired,
        reasonForNotRepaired: this.buybackReasonForm.value.reasonForNotRepaired,
        anyPhysicalDamage: this.buybackReasonForm.value.anyPhysicalDamage,
        vehicleInDealership: this.buybackReasonForm.value.vehicleInDealership,
        justification:this.buybackReasonForm.value.justification,
        additionalInfo: this.buybackReasonForm.value.additionalInfo,
        buyback:{
          buybackId:this.buybackFromDB.buybackId
        }
      }
      console.log(this.newBuybackReasonForm);
      this.service.createReason(this.newBuybackReasonForm).subscribe(result=>{
        console.log(result);
        this.showBuybackCategory.emit(true);
      })
      
    }else{
      //Updating a buyback reason
      this.newBuybackReasonForm={
        buybackReasonId:this.buybackFromDB.buybackReason.buybackReasonId,
        nonConfirmity:this.buybackReasonForm.value.nonConfirmity,
        isBuybackReasonRepaired: this.buybackReasonForm.value.isBuybackReasonRepaired,
        reasonForNotRepaired: this.buybackReasonForm.value.isBuybackReasonRepaired ? '' : this.buybackReasonForm.value.isBuybackReasonRepaired ,
        anyPhysicalDamage: this.buybackReasonForm.value.anyPhysicalDamage,
        vehicleInDealership: this.buybackReasonForm.value.vehicleInDealership,
        justification:this.buybackReasonForm.value.justification,
        additionalInfo: this.buybackReasonForm.value.additionalInfo,
        buyback:{
          buybackId:this.buybackFromDB.buybackId
        }
      }
  
      console.log(this.newBuybackReasonForm);

      const shallowComparison =
      Object.keys(this.newBuybackReasonForm).length === Object.keys(this.buybackFromDB.buybackReason).length &&
      (Object.keys(this.newBuybackReasonForm) as (keyof typeof this.buybackFromDB.buybackReason)[]).every((key) => {
        return (
          Object.prototype.hasOwnProperty.call(this.newBuybackReasonForm, key) && this.buybackFromDB.buybackReason[key] === this.newBuybackReasonForm[key]
        );
      });
      if(!shallowComparison){
        this.service.updateReason(this.newBuybackReasonForm,this.buybackFromDB.buybackReason.buybackReasonId).subscribe(result=>{
          console.log(result);
          this.showBuybackCategory.emit(true);
        }) 
      }
      this.newDealerForm={
        dealerId:this.dealerDetailsForm.value.dealerId,
        code: this.dealerDetailsForm.value.code,
        dealershipName: this.dealerDetailsForm.value.dealershipName,
        emailId: this.dealerDetailsForm.value.emailId,
        contact: this.dealerDetailsForm.value.contact
      }
      console.log(this.newDealerForm);
      if(this.dealerDetailsForm.valid){
        this.service.updateDealer(this.newDealerForm.dealerId,this.newDealerForm).subscribe((result)=>{
          console.log(result);
        })
      }
     


    }

    
   
  }

}


