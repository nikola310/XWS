import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { LoginInterface } from "../app/login-interface";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = "http://localhost:8089/booking/login";

  constructor(private http: HttpClient) { }

  login(userData){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }

    return this.http.post<LoginInterface>(this.baseUrl + '/admin', userData, config);                
  }

}
