import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx';
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
  
  manageComment(id: number, version: number, op: boolean, token: string){
     const body = new HttpParams().set('id', id.toString()).set('version', version.toString());
     const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Token': token })
    };
	  if(op){
			return this.http.post(this.baseUrl + '/accept', body, httpOptions).catch(this.handleError);
	  }else{
			return this.http.post(this.baseUrl + '/reject', body, httpOptions).catch(this.handleError);
	  }
  }
}
