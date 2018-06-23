import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private username:string = '';
  private password:string = '';

  constructor(public userService:UserService) { }

  onSubmit() {
    let login_data = { 'username':this.username, 'password':this.password };
    console.log(login_data);
  	this.userService.login(login_data).subscribe(res => console.log(res));
  	this.username = '';
  	this.password = '';
  }

}
