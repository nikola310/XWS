import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx';
import { BonusInterface } from "../app/bonus-interface";

@Injectable({
  providedIn: 'root'
})
export class BonusService {

  private baseUrl = "http://localhost:8089/booking";

  constructor(private http: HttpClient) { }

  getBonus(): Observable<BonusInterface[]> {
    return this.http.get<BonusInterface[]>(this.baseUrl + '/bonus').catch(this.handleError);
  }

  editBonus(dto: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Token': sessionStorage.getItem('Token') })
    };

    return this.http.post(this.baseUrl + '/bonus', dto, httpOptions).catch(this.handleError);
  }

  createBonus(name: string) {
    const body = new HttpParams().set('name', name);
    const options = {
      headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Token': sessionStorage.getItem('Token') })
    };
    return this.http.put(this.baseUrl + '/bonus', body, options).catch(this.handleError);
  }

  public delete(id: number){
    const options = {
      headers: new HttpHeaders({ 'Token' : sessionStorage.getItem('Token') })
    };
    return this.http.delete(this.baseUrl + '/bonus/' + id, options).catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
}
