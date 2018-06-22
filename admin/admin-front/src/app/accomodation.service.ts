import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx';
import { Accomodation } from "../app/accomodation";
import { AccRank } from "../app/acc-rank";

@Injectable({
  providedIn: 'root'
})
export class AccomodationService {

  private baseUrl = "http://localhost:8089/booking/types";
  private accType = "http://localhost:8089/booking";

  constructor(private http: HttpClient) { }

  public getAccTypes(): Observable<Accomodation[]> {
    return this.http.get<Accomodation[]>(this.baseUrl).catch(this.handleError);
  }

  public edit(data: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Token': sessionStorage.getItem('Token') })
    };

    return this.http.post(this.baseUrl, data, httpOptions).catch(this.handleError);
  }

  public delete(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Token': sessionStorage.getItem('Token') })
    };

    return this.http.delete(this.baseUrl + '/' + id, httpOptions).catch(this.handleError);
  }

  public newAccType(name: string) {
    const body = new HttpParams().set('name', name);
    const options = {
      headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Token': sessionStorage.getItem('Token') })
    };
    return this.http.put(this.baseUrl, body, options).catch(this.handleError);
  }

  public getAccomodations(): Observable<AccRank[]>{
    return this.http.get<AccRank[]>(this.accType + '/acc_admin').catch(this.handleError);
  }
  
  public setRank(dto: string){
    const options = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Token': sessionStorage.getItem('Token') })
    };
    return this.http.post(this.accType + '/acc_category', dto, options).catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }

}
