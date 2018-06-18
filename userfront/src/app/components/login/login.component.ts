import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private data;
  private token;

  constructor(private dataService: DataService) { }

  ngOnInit() {
  }

  login(data) {
    this.data = "{\"userName\":\"" + data.username + "\","
      + " \"password\":\"" + data.password + "\"}";
    this.dataService.login(this.data).subscribe((response) => {
      console.log(response);
      if (response.status == "success") {
        this.token = response.info;
      }
    });
  }
}
