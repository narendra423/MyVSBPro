import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { VbbsServiceService } from 'src/app/services/vbbs-service.service';
import { BuybackdetailsComponent } from '../buybackdetails/buybackdetails.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  showCancelAll= false;
  @Input() enableCancelAndSubmitForApproval = false;
  @Input() enableSubmitAndApprove = false;

  @Output() searchInput: EventEmitter<String> = new EventEmitter();

  constructor(private cookies:CookieService,private service:VbbsServiceService, private router: Router) { }

  ngOnInit(): void { 
    
   }


  onClickSubmitForApproval(){
    console.log("submit for approval called")
    const buyback = JSON.parse(localStorage.getItem('buybackDetails') || '{}');
    const buybackObj = {buybackId: buyback.buybackId, buybackStatus: "APPROVALPENDING"};

    this.service.updateBuybackStatus(buybackObj).subscribe(result => {
      console.log(result);
      alert("Buyback submitted for approval");
    })
  }

  onClickSubmitAndApprove(){
    console.log("submit and approved called")
    const buyback = JSON.parse(localStorage.getItem('buybackDetails') || '{}');
    const buybackObj = {buybackId: buyback.buybackId, buybackStatus: "APPROVED"};

    this.service.updateBuybackStatus(buybackObj).subscribe(result => {
      console.log(result);
      alert("Buyback approved and submitted successfully");
    })
  }

  onClickCancel(){
    console.log("cancel called")
    const buyback = JSON.parse(localStorage.getItem('buybackDetails') || '{}');
    const buybackObj = {buybackId: buyback.buybackId, buybackStatus: "CANCELLED"};

    this.service.updateBuybackStatus(buybackObj).subscribe(result => {
      console.log(result);
      alert("Buyback Cancelled successfully");
      window.location.reload();
    })
  }

  onClickClearSearch(){
    this.searchInput.emit("");
  }

  onClickClearAll(){
    window.location.reload();
  }

  onClickLogout(){
    this.cookies.delete('jwt_token');
    localStorage.removeItem('buybackDetails');
    this.router.navigateByUrl('/login');
  }

}
