import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from "../login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private data;

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit() {
  }

  login(data) {
    this.data = "{\"userName\":\"" + data.username + "\","
      + " \"password\":\"" + data.password + "\"}";
    this.loginService.login(this.data).subscribe((response) => {
      console.log(response);
      if (response.status == "success") {
        sessionStorage.setItem('Token', response.info);
        this.router.navigate(['/users']);
      }
    });
  }

}
