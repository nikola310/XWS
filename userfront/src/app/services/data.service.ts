import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IPost } from '../interfaces/post';
import { Observable } from 'rxjs/Observable';
import { IAccomodation } from '../interfaces/accomodation';
import { IBonusService } from '../interfaces/bonusservice';
import { IAccomodationType } from '../interfaces/accomodationtype';
import { ILoginResponse } from '../interfaces/loginresponse';
import 'rxjs/add/operator/map';
@Injectable()
export class DataService {

  private _posts: string = "https://jsonplaceholder.typicode.com/posts";
  private url: string;

  constructor(private http: HttpClient) { 
    this.url = "";
  }


  getPosts(): Observable<IPost[]> {
    return this.http.get<IPost[]>(this._posts);
  }

  search():Observable<IAccomodation[]> {
    return this.http.get<IAccomodation[]>(this.url);
  }

  getAllAccomodations(): Observable<IAccomodation[]> {
    return this.http.get<IAccomodation[]>('http://localhost:8089/booking/accomodations');
  }

  getAllBonusServices(): Observable<IBonusService[]> {
    return this.http.get<IBonusService[]>('http://localhost:8089/booking/bonusservices');
  }

  getAllAccomodationTypes(): Observable<IAccomodationType[]>{
    return this.http.get<IAccomodationType[]>('http://localhost:8089/booking/types');
  }

  setUrl(url){
    this.url = url;
  }

  register(userData){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post('http://localhost:8089/booking/register', userData, config);
  }

  login(userData){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post<ILoginResponse>('http://localhost:8089/booking/login', userData, config);
                    
  }
}
