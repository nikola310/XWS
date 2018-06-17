import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx';
//import 'rxjs/add/operator/map';
import { CommentInterface } from "../app/comment-interface";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = "http://localhost:8089/booking/comment";

  constructor(private http: HttpClient) { }
  
  getComments(): Observable<CommentInterface[]> {
    return this.http.get<CommentInterface[]>(this.baseUrl + '/to_manage').catch(this.handleError);
  }
  
  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
  
  manageComment(id: number, version: number, op: boolean){
	   const body = new HttpParams().set('id', id).set('version', version);
	  if(op){
			return this.http.post(this.baseUrl + '/accept', body, new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded'})).catch(this.handleError);
	  }else{
			return this.http.post(this.baseUrl + '/reject', body, new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded'})).catch(this.handleError);
	  }
  }
}
