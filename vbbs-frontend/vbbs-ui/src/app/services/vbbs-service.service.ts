import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root'
})
export class VbbsServiceService {

  BASE_URL = "http://localhost:1010"

  constructor(private http:HttpClient) { }
  
  getVehicleByVin(vin :any):Observable<any>{
    return this.http.get(this.BASE_URL+`/vehicle/get/${vin}`)
  }

  checkBranchAdmin(emailId: string,password: string): Observable<any>{
    const params = new HttpParams().set('emailId',emailId).set('password',password);
    return this.http.get(`${this.BASE_URL}/user/login`,{params});
  }

  updateCustomer(data:any):Observable<any>{
    return this.http.put<any>(this.BASE_URL+"/customer/updatecustomer",data);
  }

/*  
  THis Api called by @SAI 

  Decision Maker Details

*/
  getDecisionMakerById(decisionMakerId:any):Observable<any>{
    return this.http.get(this.BASE_URL+`/business-centre/business/${decisionMakerId}`)
  }
  getAllUsers():Observable<any>{
    return this.http.get<any>(this.BASE_URL+`/user/getalluser`)

  }

  /*
    buyback create
  */
  createBuyback(data:any):Observable<any>{
    return this.http.post(this.BASE_URL+"/buyback/buyback",data)
  }

  updateBuyback(id:any,data:any):Observable<any>{
    return this.http.put(this.BASE_URL+`/buyback/buyback/update/${id}`,data)
   }

  updateBuybackStatus(buyback: any): Observable<any>{
    return this.http.put(this.BASE_URL + `/buyback/update`, buyback);
  }

  //create buybackCategory
  createCategory(data:any,buybackreasonId:number){
    return this.http.post(this.BASE_URL+`/buyback-category/category/${buybackreasonId}`,data)
  }
  getCategoryDetails(){
    return this.http.get(this.BASE_URL+"/buyback-category/readAll");
  }
  //create buybackreason
  createReason(data:any){
    return this.http.post(this.BASE_URL+"/buyback-reason/buybackReason",data)
  }

  updateReason(data:any,id:number):Observable<any>{
    return this.http.put(this.BASE_URL+`/buyback-reason/update/${id}`,data)
  }

  getReasonDetails():Observable<any>{
    return this.http.get(this.BASE_URL+"/buyback-reason/getAll")
  }
  //Dealer Service
  createDealer(data:any){
    return this.http.post(this.BASE_URL+"/dealer/create",data)
  }

  getDealerDetailsbyCode(dealerCode:any):Observable<any>{
    return this.http.get(this.BASE_URL+`/dealer/getByCode/${dealerCode}`)
  }

  updateDealer(id:number,data:any):Observable<any>{
    return this.http.put(this.BASE_URL+`/dealer/update/${id}`,data)
  }

  uploadAttachmentsByBuybackId(buybackId: number, data:any):Observable<any> {
    return this.http.post(this.BASE_URL+`/attachment/uploadFile/${buybackId}`, data )
  }
  getAttachmentsByBuybackId(buybackId: number):Observable<any> {
    return this.http.get<any>(this.BASE_URL+`/attachment/buyBackAttachment/${buybackId}`)
  }
  deleteFile(fileId: any):Observable<any> {
    return this.http.delete(this.BASE_URL+`/attachment/deletebyid/${fileId}`)
  }



  /*
  Primary Category */

  createPrimaryCategory(buybackreasonId:number,data:any):Observable<any>{
    return this.http.post(this.BASE_URL+`/buyback-category/category/${buybackreasonId}`,data);
  }
  updatePrimaryCategory(buybackCategoryId:number,data:any):Observable<any>{
    return this.http.put(this.BASE_URL+`/buyback-category/updateById/${buybackCategoryId}`,data);
  }
  //businessCentre
  updateBusinessCentre(businessId:number,data:any):Observable<any>{
    return this.http.put(this.BASE_URL+`/business-centre/business/${businessId}`,data);
  }

  createStudent(user: User): Observable<User> {
    return this.http.post<User>(`${this.BASE_URL}/user/create`, user);
  }

}
