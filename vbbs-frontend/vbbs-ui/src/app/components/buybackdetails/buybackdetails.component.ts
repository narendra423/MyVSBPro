import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { VbbsServiceService } from '../../services/vbbs-service.service';

@Component({
  selector: 'app-buybackdetails',
  templateUrl: './buybackdetails.component.html',
  styleUrls: ['./buybackdetails.component.css']
})

export class BuybackdetailsComponent implements OnInit {
  arbitrationToggle = true;
  offsetToggle = true;
  status = 'Enable';
  showOffsetDetails=true;
  showAftermarketAccessoriesList=true;
  createBuyback:any;
  updateBuyback:any;
  
  flatUsageFeeDisable = true;
  straightMileageDisable = true;
  flatUsageFeeTemp : any;
  straightMileageMilesTemp: any;
  straightMileageCostPerMileTemp: any;
  

  @Input() vehicleInfo:any;

  @Output() showBuybackReason: EventEmitter<Boolean> = new EventEmitter();
  @Output() enableCancelAndSubmitForApproval: EventEmitter<Boolean> = new EventEmitter();

  constructor(private fb: FormBuilder,
    private service:VbbsServiceService) { }

  buybackDetailsForm=this.fb.group({
    isArbitrationFilled:[true,[Validators.required]],
    typeOfBuyback:['',[Validators.required]],
    isStericycleNegotiate:['',[Validators.required]],
    isMileageOffset:[true,[Validators.required]],
    aftermarketAccessoriesList:['',[Validators.required]],
    isAftermarketAccessories:['',[Validators.required]],
    flatUsageFee:[0,[Validators.required]],
    isFlatUsageFee:['',[Validators.required]],
    isStraightMilage:['',[Validators.required]],
    straightMilageMiles:[0,[Validators.required]],
    straightMilageCostPerMile:[0,[Validators.required]],
    purchasedPrice:[0,[Validators.required]]
  })
  buyback:any;
  vehicle:any;
  ngOnInit(): void {
   this.buyback = this.vehicleInfo.buyback;
   console.log(this.buyback)
   

   if(this.buyback != null && this.buyback.buybackStatus !== "CANCELLED"){

   this.offsetToggle = this.buyback.isMileageOffset;
   this.arbitrationToggle = this.buyback.isArbitrationFilled;
   this.flatUsageFeeDisable = !this.buyback.isFlatUsageFee;
   this.straightMileageDisable = !this.buyback.isStraightMilage;
   this.flatUsageFeeTemp = this.buyback.flatUsageFee;
   this.straightMileageMilesTemp = this.buyback.straightMilageMiles;
   this.straightMileageCostPerMileTemp = this.buyback.straightMilageCostPerMile;

    if(this.buyback.isAftermarketAccessories==="yes"){
      this.AftermarketAccessoriesshow();
     }else{
      this.AftermarketAccessorieshide();
     }
     this.showOffsetDetails = this.buyback.isMileageOffset;
    this.buybackDetailsForm=this.fb.group({
      isArbitrationFilled:[this.buyback.isArbitrationFilled,[Validators.required]],
      typeOfBuyback:[this.buyback.typeOfBuyback,[Validators.required]],
      isStericycleNegotiate:[String(this.buyback.isStericycleNegotiate),[Validators.required]],
      isMileageOffset:[this.buyback.isMileageOffset,[Validators.required]],
      aftermarketAccessoriesList:[this.buyback.aftermarketAccessoriesList,[Validators.required]],
      isAftermarketAccessories:[this.buyback.isAftermarketAccessories,[Validators.required]],
      flatUsageFee:[this.buyback.flatUsageFee,[Validators.required]],
      isFlatUsageFee:[this.buyback.isFlatUsageFee,[Validators.required]],
      isStraightMilage:[this.buyback.isStraightMilage,[Validators.required]],
      straightMilageMiles:[this.buyback.straightMilageMiles,[Validators.required]],
      straightMilageCostPerMile:[this.buyback.straightMilageCostPerMile,[Validators.required]],
      purchasedPrice:[this.buyback.purchasedPrice,[Validators.required]]
    })
   }

  }

  toggleArbitration() {
    this.arbitrationToggle = !this.arbitrationToggle;
  }

  toggleOffset() {
    this.offsetToggle = !this.offsetToggle;
    this.showOffsetDetails = !this.showOffsetDetails;
  }
  AftermarketAccessoriesshow() {
    this.showAftermarketAccessoriesList = false;
  }
  AftermarketAccessorieshide() {
    this.showAftermarketAccessoriesList = true;
  }

  buybackDetailsSubmit(){

    if(this.buyback == null){
      this.createBuyback={
        buybackStatus: 'DRAFT',
        isArbitrationFilled:this.buybackDetailsForm.value.isArbitrationFilled,
        typeOfBuyback:this.buybackDetailsForm.value.typeOfBuyback,
        isStericycleNegotiate:this.buybackDetailsForm.value.isStericycleNegotiate,
        isMileageOffset:this.buybackDetailsForm.value.isMileageOffset,
        aftermarketAccessoriesList:this.buybackDetailsForm.value.isAftermarketAccessories === "yes" ? this.buybackDetailsForm.value.aftermarketAccessoriesList : '',
        isAftermarketAccessories:this.buybackDetailsForm.value.isAftermarketAccessories,
        flatUsageFee: this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.flatUsageFee : 0,
        isFlatUsageFee: this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.isFlatUsageFee : false,
        isStraightMilage:  this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.isStraightMilage : false,
        straightMilageMiles: this.buybackDetailsForm.value.isMileageOffset ===true ?  this.buybackDetailsForm.value.straightMilageMiles : 0,
        straightMilageCostPerMile:  this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.straightMilageCostPerMile : 0,
        vehicle:{
          vehicleId:this.vehicleInfo.vehicleId
        }
      }
      this.service.createBuyback(this.createBuyback).subscribe(data => {
        console.log(data)
        this.showBuybackReason.emit(true);
        this.enableCancelAndSubmitForApproval.emit(true);
      })
    }
    
    else if (this.buyback != null){
      this.updateBuyback={
        buybackStatus: 'DRAFT',
        isArbitrationFilled:this.buybackDetailsForm.value.isArbitrationFilled,
        typeOfBuyback:this.buybackDetailsForm.value.typeOfBuyback,
        isStericycleNegotiate:this.buybackDetailsForm.value.isStericycleNegotiate,
        isMileageOffset:this.buybackDetailsForm.value.isMileageOffset,
        aftermarketAccessoriesList: this.buybackDetailsForm.value.isAftermarketAccessories === "yes" ? this.buybackDetailsForm.value.aftermarketAccessoriesList : '',
        isAftermarketAccessories:this.buybackDetailsForm.value.isAftermarketAccessories,
        flatUsageFee: this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.flatUsageFee : 0,
        isFlatUsageFee: this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.isFlatUsageFee : false,
        isStraightMilage:  this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.isStraightMilage : false,
        straightMilageMiles: this.buybackDetailsForm.value.isMileageOffset ===true ?  this.buybackDetailsForm.value.straightMilageMiles : 0,
        straightMilageCostPerMile:  this.buybackDetailsForm.value.isMileageOffset === true ? this.buybackDetailsForm.value.straightMilageCostPerMile : 0,
        vehicle:{
          vehicleId:this.vehicleInfo.vehicleId
        }
      }
    
      const shallowComparison =
          Object.keys(this.vehicleInfo.buyback).length === Object.keys(this.updateBuyback).length &&
          (Object.keys(this.vehicleInfo.buyback) as (keyof typeof this.vehicleInfo.buyback)[]).every((key) => {
            return (
              Object.prototype.hasOwnProperty.call(this.updateBuyback, key) && this.vehicleInfo.customer[key] === this.updateBuyback[key]
            );
          });

    if(!shallowComparison){
      this.service.updateBuyback(this.buyback.buybackId,this.updateBuyback).subscribe(data=>{
        console.log(data);
        this.showBuybackReason.emit(true);
        this.enableCancelAndSubmitForApproval.emit(true);
        })
    }
  
    }
  }
  disablingInput1(e:any){
    if (e.target.checked) {
      this.flatUsageFeeDisable=false;
    } else {
      this.flatUsageFeeDisable=true;
    }
  }
  disablingInput2and3(e:any){
    if (e.target.checked) {
      this.straightMileageDisable=false;
    } else {
      this.straightMileageDisable=true;
    }
  }

  getFlatUsageFee(e: any){
    this.flatUsageFeeTemp  = parseFloat(e.target.value);
  }

  getCurrentStraightMileageMiles(e: any){
    this.straightMileageMilesTemp = e.target.value;
  }

  getCurrentStraightMileageCostPerMile(e: any){
    this.straightMileageCostPerMileTemp = e.target.value;
  }
  

}
