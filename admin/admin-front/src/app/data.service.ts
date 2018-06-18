import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { LoginInterface } from './login-interface';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private token: string;

  constructor() { }

  setToken(token: string){
    this.token = token;
  }

  getToken(): string{
    return this.token;
  }
}
