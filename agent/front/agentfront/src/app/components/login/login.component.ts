import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private username:string = '';
  private password:string = '';

  constructor(public userService:UserService, private router:Router) { }

  onSubmit() {
    let login_data = { 'userName':this.username, 'password':this.password };
    this.userService.setUsername(this.username);
    console.log(login_data);
  	this.userService.login(login_data).subscribe(res => {
      console.log(res);
      if(res.status == "success"){
        this.userService.setLogged();
        this.userService.setUserId(res.info);
        this.router.navigateByUrl('/new');
      }
    });
  	this.username = '';
  	this.password = '';
  }

}
