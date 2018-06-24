import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { ILoginResponse } from '../interfaces/loginres';

@Injectable()
export class UserService {

  private jsonToken: string = null;
  public serviceMessages: Observable<String>;
  private logged: Boolean = false;
  private username: String;
  private userId: String;
  constructor(public http: Http) { }

  testHttp() {
    return this.http.get('http://localhost:5000/login').map(res => res.json());
  }

  testHttp2() {
    this.testHttp().subscribe(res => {
      console.log(res.data);
    });
  }

  login(login_data){
    
    //return this.http.post('http://localhost:8090/agent/user/login', login_data).map(res => res.json());
    return this.http.post('http://localhost:8090/agent/loginagent', login_data).map(res => res.json());
  }

  logout() {
    console.log('User logout.');
    this.jsonToken = '';
    this.serviceMessages = new Observable(observer => {
      observer.next('user_status,offline');
      observer.complete();
    });
  }

  register(userData, locationData){

  }

  setLogged(){
    this.logged = !this.logged;
  }
  setUserId(id){
    this.userId = id;
  }
  setUsername(username){
    this.username = username;
  }

  getLogged(){
    return this.logged;
  }
  getUserId(){
    return this.userId;
  }
  getUsername(){
    return this.username;
  }
}
