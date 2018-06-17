import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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
  private options = {
    headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' })
  };

  constructor(private http: HttpClient) { }

  getUsers(): Observable<UserInterface[]> {
    return this.http.get<UserInterface[]>(this.baseUrl).catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }

  activate(id: number, version: number){
    const body = new HttpParams().set('id', id.toString()).set('version', version.toString());
    return this.http.post(this.baseUrl + '/activate', body, this.options).catch(this.handleError);
  }

  block(id: number, version: number){
    const body = new HttpParams().set('id', id.toString()).set('version', version.toString());
    return this.http.post(this.baseUrl + '/block', body, this.options).catch(this.handleError);
  }

  delete(id: number){
    return this.http.delete(this.baseUrl + '/' + id).catch(this.handleError);
  }

}
