import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx';
//import 'rxjs/add/operator/map';
import { UserInterface } from "../app/user-interface";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = "http://localhost:8089/booking/user";

  constructor(private http: HttpClient) { }

  getUsers(): Observable<UserInterface[]> {
    return this.http.get<UserInterface[]>(this.baseUrl + '/normal').catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }

}
