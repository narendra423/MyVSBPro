import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable, Subject } from 'rxjs';
import { AuthserviceService } from 'src/app/services/authservice.service';
import { VbbsServiceService } from 'src/app/services/vbbs-service.service';

@Component({
  selector: 'app-attachment',
  templateUrl: './attachment.component.html',
  styleUrls: ['./attachment.component.css']
})
export class AttachmentComponent implements OnInit {
  myFiles:any = [];
  listOfFiles: Array<any> = [];

  selectedFile :any= null;
  
  @Input() vehicleInfo: any;
  enableAdd= false;

  attachment = this._formBuilder.group({
    file: []
  });

  constructor(private _formBuilder: FormBuilder,
    private service:VbbsServiceService, private cookies: CookieService, private router: Router, private auth: AuthserviceService) {}
    serveCall(){
      this.service.getAttachmentsByBuybackId(this.vehicleInfo.buyback.buybackId).subscribe( (data)=>{
        this.myFiles = data;
      })
    }
  ngOnInit(): void {
    const data = JSON.parse(localStorage.getItem('vehicleDetails') || '{}');
    this.service.getVehicleByVin(data.vin).subscribe(result => {
      localStorage.setItem('buybackDetails', JSON.stringify(result.buyback));
      this.vehicleInfo = result;
    });
   
    if(this.vehicleInfo.buyback.attachments.length>0){
      this.serveCall();
    }
  }
  

  onFileSelected(event:any) {
    this.selectedFile = event.target.files[0];
    console.log(this.vehicleInfo);
    this.enableAdd = true;
  }

  convertFile(file:File):Observable<string>{
    const result = new Subject<string>();
    const reader = new FileReader();

    reader.readAsBinaryString(file);
    reader.onload = (event:any) => result.next(event?.target.result.toString());
    return result;
  }

  uploadFilesToDb(){
    this.convertFile(this.selectedFile).subscribe(b=>{
      const attachmentFile = {
        fileName:this.selectedFile.name,
        file:btoa(b),
        fileType:this.selectedFile.type
      }
      this.service.uploadAttachmentsByBuybackId(this.vehicleInfo.buyback.buybackId, attachmentFile).subscribe((data) => {
        this.serveCall();
        console.log("my files is:"+this.myFiles);
      })
    })
    this.enableAdd = false;
    this.attachment.value.file = null;
  }
  // getFiles(event: any) {
  //   this.btnClick = this.btnClick;
  //   this.listOfFiles.push(this.btnClick);
  //   for (var i = 0; i < event.target.files.length; i++) { 
  //     this.listOfFiles.push(event.target.files[i]);
  //   }
  //   console.log(this.listOfFiles)
  // }
 
  deleteClicked(item: any) {
    console.log(item);
    this.service.deleteFile(item.attachmentId).subscribe(() => { 
      //console.log(id)
      //this.serveCall();
      this.myFiles = this.myFiles.filter((a:any)=> a.attachmentId != item.attachmentId )
      
    });

    
  }
}
