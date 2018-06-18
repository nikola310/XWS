import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from "../login.service";
import { DataService } from "../data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private data;
  private token;

  constructor(private router: Router, private loginService: LoginService, private dataService: DataService) { }

  ngOnInit() {
  }

  login(data) {
    this.data = "{\"userName\":\"" + data.username + "\","
      + " \"password\":\"" + data.password + "\"}";
    this.loginService.login(this.data).subscribe((response) => {
      console.log(response);
      if (response.status == "success") {
        this.token = response.info;

        this.dataService.setToken(this.token);
        this.router.navigate(['/home']);
      }
    });
  }

}
