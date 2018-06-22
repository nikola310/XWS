import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private data;
  private token;
  private loginFail;

  constructor(private dataService: DataService, private router:Router) { }

  ngOnInit() { 
    this.loginFail = false;
  }

  login(data) {
    this.data = "{\"userName\":\"" + data.username + "\","
      + " \"password\":\"" + data.password + "\"}";
    this.dataService.login(this.data).subscribe((response) => {
      console.log(response);
      if (response.status == "success") {
        this.token = response.info;
        this.dataService.setLogged(true);
        this.dataService.setToken(this.token);
        this.dataService.setUsername(data.username);
        this.dataService.setUserId(response.userId);
        this.router.navigateByUrl("/");
        this.loginFail=false;
        
      }else{
        this.loginFail=true;
      }
    });
  }

  
}
