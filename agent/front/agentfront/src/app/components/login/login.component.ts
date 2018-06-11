import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username:string = '';
  password:string = '';

  constructor(public userService:UserService) { }

  ngOnInit() { }

  onSubmit() {
  	this.userService.login(this.username, this.password);
  	this.username = '';
  	this.password = '';
  }

  handleMessage() {
    this.userService.getServiceMessages().subscribe(serviceMessages => {
      let msgData = serviceMessages.split(',');
      if(msgData[0] == 'login') {
        switch (msgData[1]) {
          case "user_status":
            //this.logged = (this.msgData[2]=='online')?true:false;
            break;
          
          default:
            console.log('Service message: ' + serviceMessages);
            break;
        }
      }
    });
  }

}
