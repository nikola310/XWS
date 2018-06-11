import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IPost } from '../interfaces/post';
import { Observable } from 'rxjs/Observable';
import { IAccomodation } from '../interfaces/accomodation';

@Injectable()
export class DataService {

  private _posts: string = "https://jsonplaceholder.typicode.com/posts";

  constructor(private http: HttpClient) { }


  getPosts(): Observable<IPost[]> {
    return this.http.get<IPost[]>(this._posts);
  }

  search(url) {
    this.http.get<any[]>(url);
    console.log("searched");
  }

  getAllAccomodations(): Observable<IAccomodation[]> {
    return this.http.get<IAccomodation[]>('http://localhost:8089/booking/accomodations');
  }
}
