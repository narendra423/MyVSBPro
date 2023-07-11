import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { VbbsServiceService } from 'src/app/services/vbbs-service.service';


@Component({
  selector: 'app-buybackcategory',
  templateUrl: './buybackcategory.component.html',
  styleUrls: ['./buybackcategory.component.css']
})
export class BuybackcategoryComponent implements OnInit {
  DealerDetails: any;
  dealeropinionToggle = true;
  status = 'Enable';
  showOffsetDetails = true;
  btnClick = 0;
  listOfFiles: Array<any> = [];
  businesscentredetails: any;
  @Input() buybackFromDB: any;
  buybackReasonFromDB: any;
  newBuybackPrimaryCategoryForm: any;
  buybackCategoryTemp: any;
  updateBusinessCenterDetails: any;
  businessCenterTemp: any;
  updateBuybackPrimaryCategoryForm: any;
  newBussinessCenter:any;

  @Output() showAttachement: EventEmitter<Boolean> = new EventEmitter();
  @Output() enableSubmitAndApprove: EventEmitter<Boolean> = new EventEmitter();

  primaryCategoryForm = this.formBuilder.group({
    primaryCategory: ['select', Validators.required],
    problem: ['select', Validators.required],
    problemDesc: ['', Validators.required]

  })

  businessCentreForm = this.formBuilder.group({
    businessId: [''],
    decisionMakerId: ['', Validators.required],
    decisionMaker: ['', Validators.required],
    phone: ['', Validators.required],
    emailId: ['', Validators.required],
    businessCentreIdentity: ['', Validators.required],
    district: ['', Validators.required],
    isCauseForBuybackByDealer: ['']

  });



  selectedPrimarryCategory: any;
  problems: any;

  primaryCategories = [
    {
      id: 1, name: 'Engine', problems: ['Poor lubrication',
        'Failing oil pump',
        'Oil deposits and debris',
        'Inadequate fuel and air compression',
        'Leaking engine coolant',
        'Blocked engine radiators',
        'Prolonged engine detonation',
        'Damaged oxygen sensors']
    },
    {
      id: 2, name: 'Mileage', problems: ['Air Filter',
        'Oxygen Sensors',
        'Spark Plugs',
        ' Fuel System',
        ' Air Conditioner',
        'Exhaust System',
        'Motor Oil']
    },
    {
      id: 3, name: 'Allignment', problems: ['Uneven or rapid tire wear',
        'Steering wheel being crooked when you are driving straight',
        ' Noisy Steering',
        '  Pulling to the right or left',
        'Squealing tires']
    },
    {
      id: 4, name: 'Chasis', problems: ['Backbone Chassis',
        ' Ladder Chassis',
        'Tubular Chassis',
        'Monocoque Chassis']
    },
  ];


  changeCategory(deviceValue: any) {
    this.problems = this.primaryCategories.filter(x => x.name === deviceValue.value)[0].problems;
  }

  constructor(private formBuilder: FormBuilder,
    private service: VbbsServiceService) { }
  ngOnInit() {
    const data = JSON.parse(localStorage.getItem('vehicleDetails') || '{}');
    this.service.getVehicleByVin(data.vin).subscribe(result => {
      localStorage.setItem('buybackDetails', JSON.stringify(result.buyback));
    });

    this.businessCenterTemp = this.buybackFromDB.businessCentre;
    this.buybackReasonFromDB = this.buybackFromDB.buybackReason;
    console.log(this.buybackReasonFromDB)
    this.problems = this.primaryCategories.filter(x => x.name.includes("Engine"))[0].problems;
 
    if (this.buybackReasonFromDB.buybackCategories != null) {
      this.buybackCategoryTemp = this.buybackReasonFromDB.buybackCategories;
      console.log(this.buybackCategoryTemp[0])

      this.primaryCategoryForm = this.formBuilder.group({
        primaryCategory: [this.buybackCategoryTemp[0].primaryCategory, [Validators.required]],
        problem: [this.buybackCategoryTemp[0].problem, [Validators.required]],
        problemDesc: [this.buybackCategoryTemp[0].problemDesc, [Validators.required]]
      })

      this.businessCentreForm = this.formBuilder.group({
        businessId: [this.buybackFromDB.businessCentre.businessId, Validators.required],
        decisionMakerId: [this.buybackFromDB.businessCentre.decisionMakerId, Validators.required],
        decisionMaker: [this.buybackFromDB.businessCentre.decitionMaker, Validators.required],
        phone: [this.buybackFromDB.businessCentre.phone, Validators.required],
        emailId: [this.buybackFromDB.businessCentre.emailId, Validators.required],
        businessCentreIdentity: [this.buybackFromDB.businessCentre.businessCentreIdentity, Validators.required],
        district: [this.buybackFromDB.businessCentre.district, Validators.required],
        isCauseForBuybackByDealer: [this.buybackFromDB.businessCentre.isCauseForBuybackByDealer]
      });
    }
  
  }


  toggleDealerOpinion() {
    this.dealeropinionToggle = !this.dealeropinionToggle;
  }
  testing(e:any){
    var val = (<HTMLInputElement>document.getElementById("categoryProblem")).value;
    console.log(val);
    console.log(e.target.value);
    console.log(this.primaryCategoryForm.value.problem)
  }

  getDecisionMaker() {
    //this.DecisionMakerDetails.reset();
    this.service.getDecisionMakerById(this.businessCentreForm.value.decisionMakerId).subscribe((data) => {
      this.businesscentredetails = data;
      console.log(this.businesscentredetails);
      console.log("search working");
      this.businessCentreForm = this.formBuilder.group({
        businessId: [this.businesscentredetails.businessId],
        decisionMakerId: [this.businesscentredetails.decisionMakerId],
        decisionMaker: [this.businesscentredetails.decitionMaker],
        phone: [this.businesscentredetails.phone],
        emailId: [this.businesscentredetails.emailId],
        businessCentreIdentity: [this.businesscentredetails.businessCentreIdentity],
        district: [this.businesscentredetails.district],
        isCauseForBuybackByDealer: [this.businesscentredetails.isCauseForBuybackByDealer],
      })
    });
  }

  categoryAndBusinessCentreSubmit() {
    this.newBuybackPrimaryCategoryForm = {
      primaryCategory: this.primaryCategoryForm.value.primaryCategory,
      problem: this.primaryCategoryForm.value.problem,
      problemDesc: this.primaryCategoryForm.value.problemDesc
    }
    
    this.newBussinessCenter = {
      businessId: this.buybackFromDB.businessCentre.businessId,
      decisionMakerId:this.businessCentreForm.value.decisionMakerId,
      decisionMaker:this.businessCentreForm.value.decisionMaker,
      phone: this.businessCentreForm.value.phone,
      emailId:this.businessCentreForm.value.emailId,
      businessCentreIdentity: this.businessCentreForm.value.businessCentreIdentity,
      district: this.businessCentreForm.value.district,
      isCauseForBuybackByDealer: this.businessCentreForm.value.isCauseForBuybackByDealer
    };



    if (this.buybackReasonFromDB.buybackCategories[0] == null) {
      this.service.createPrimaryCategory(this.buybackReasonFromDB.buybackReasonId, this.newBuybackPrimaryCategoryForm).subscribe(result => {
        console.log(result);
        this.showAttachement.emit(true);
        this.enableSubmitAndApprove.emit(true);
      })

    } else {
      const shallowComparison =
        Object.keys(this.buybackReasonFromDB.buybackCategories[0]).length === Object.keys(this.newBuybackPrimaryCategoryForm).length &&
        (Object.keys(this.buybackReasonFromDB.buybackCategories[0]) as (keyof typeof this.buybackReasonFromDB.buybackCategories[0])[]).every((key) => {
          return (
            Object.prototype.hasOwnProperty.call(this.newBuybackPrimaryCategoryForm, key) && this.buybackReasonFromDB.buybackCategories[0][key] === this.newBuybackPrimaryCategoryForm[key]
          );
        });
      this.updateBuybackPrimaryCategoryForm = {
        primaryCategory: this.primaryCategoryForm.value.primaryCategory,
        problem: this.primaryCategoryForm.value.problem,
        problemDesc: this.primaryCategoryForm.value.problemDesc,
        buybackReason: {
          buybackReasonId: this.buybackReasonFromDB.buybackReasonId
        }
      }
      console.log(this.updateBuybackPrimaryCategoryForm)
        this.service.updatePrimaryCategory(this.buybackFromDB.buybackReason.buybackCategories[0].buybackCategoryId, this.updateBuybackPrimaryCategoryForm).subscribe(result => {
          console.log(result);
          this.showAttachement.emit(true);
          this.enableSubmitAndApprove.emit(true);
        });

      

    }
    const shallowComparison =
    Object.keys(this.buybackFromDB.businessCentre).length === Object.keys(this.newBussinessCenter).length &&
    (Object.keys(this.buybackFromDB.businessCentre) as (keyof typeof this.buybackFromDB.businessCentre)[]).every((key) => {
      return (
        Object.prototype.hasOwnProperty.call(this.newBussinessCenter, key) && this.buybackFromDB.businessCentre[key] === this.newBussinessCenter[key]
      );
    });
    if(shallowComparison){
      this.service.updateBusinessCentre(this.buybackFromDB.businessCentre.businessId,this.newBussinessCenter)
      this.showAttachement.emit(true);
      this.enableSubmitAndApprove.emit(true);
    }

    

  }





}
