import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {

  private jsonToken:string = null;
  public serviceMessages:Observable<String>;

  constructor(public http:Http) { }

  ngOnInit() { }

  getServiceMessages() {
    return this.serviceMessages;
  }

  testHttp() {
    return this.http.get('http://localhost:5000/login').map(res => res.json());
  }

  testHttp2() {
    this.testHttp().subscribe(res => {
      console.log(res.data)
    });
  }

  login(username, password) {
  	// http service
    this.testHttp2();

    this.serviceMessages = new Observable(observer => {
      observer.next('user_status,online');
      observer.complete();
    });
  }

  logout() {
  	console.log('User logout.');
    this.jsonToken = '';
    this.serviceMessages = new Observable(observer => {
      observer.next('user_status,offline');
      observer.complete();
    });
  }

}
