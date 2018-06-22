import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx';
import { UserInterface } from "../app/user-interface";

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  private baseUrl = "http://localhost:8089/booking/user";

  constructor(private http: HttpClient) { }

  getAgents(): Observable<UserInterface[]> {
    return this.http.get<UserInterface[]>(this.baseUrl + '/by_type/AGENT_PENDING').catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
  
  newAgent(data: any, id: number, token: string){
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Token' : token})
    };
    
	  return this.http.post(this.baseUrl + '/agent?user=' + id, data, httpOptions).catch(this.handleError);
  }
}
