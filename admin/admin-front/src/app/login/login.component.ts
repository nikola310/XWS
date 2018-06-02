import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private msg; 
  private router:Router;
  //private ws:WebsocketService;

  constructor(private rt:Router) { 
    this.router = rt;
  }

  ngOnInit() {
  }

  submitForm(data){
    this.msg = "{\"type\":\"login\","
               + " \"data\":{"
               + " \"username\":\"" + data.username + "\","
               + " \"password\":\"" + data.password +"\"}"
               + "}";
      console.log(this.msg);
      //this.ws["username"] = data.username;
      //console.log(this.ws["username"]);
      //this.ws.sendMsg(this.msg);

      /**
       * Slanje REST-a
       */
      this.router.navigate(['/home']);
  }

}
