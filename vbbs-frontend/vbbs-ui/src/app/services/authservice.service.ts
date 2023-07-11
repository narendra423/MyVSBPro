import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {
  private BASE_URL = 'http://www.localhost:1010/user'; 

  constructor(private http: HttpClient) { }

  generateToken(request:any){
    return this.http.post<any>(`${this.BASE_URL}/authenticate`,request,{responseType:'text' as 'json'})
  }

  welcome(token:any){
    let tokenStr='Bearer '+token;
    const headers= new HttpHeaders().set('Authorization',tokenStr);

    return this.http.get<String>("http://localhost:1010",{responseType:'text' as 'json'})
  }
}
