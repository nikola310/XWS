import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { IAccomodationType } from '../interfaces/accomodationtype';

@Injectable()
export class RestclientService {

  constructor(public http: HttpClient) { }

  addLocation(location){
    return this.http.post('http://localhost:8090/agent/locationagent', location);
  }

  addAccomodation(accomodation){
    console.log("uso u dodavanje");
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post('http://localhost:8090/agent/accomodationagent', accomodation, config);
  }

  addPrice(price){
    console.log("dodajemo cijenu");
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post('http://localhost:8090/agent/priceagent', price, config);
  }

  addAccomodationBonus(accBonus){

  }

  getAccomodationTypes(){
    return this.http.get<IAccomodationType[]>('http://localhost:8090/agent/accomodationtypeagent');
  }

  addPicture(picture){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post('http://localhost:8090/agent/pictureagent', picture, config);
  }

  setAccomodationAgent(agent){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post('http://localhost:8090/agent/accomodationagent/agent', agent, config);
  }
}
