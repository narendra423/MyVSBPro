import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VbbsServiceService {

  private BASE_URL = 'http://www.localhost:1010'; 


  constructor(private http: HttpClient) { }

  getAllUsers():Observable<any>{
    return this.http.get<any>(this.BASE_URL+`/user/getalluser`)
  }
  getDecisionMakerById(decisionMakerId:any):Observable<any>{
    return this.http.get(this.BASE_URL+`business-centre/business/${decisionMakerId}`)
  }
 
}
