import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private data;
  private registered = true;

  constructor(private dataService:DataService, private router:Router) { }

  ngOnInit() {
  }

  register(data) {
    console.log('registering ...');
    console.log('Your first name is ' + data.firstname);
    console.log('Your last name is ' + data.lastname);
    console.log('Your username is ' + data.username);
    console.log('Your password is ' + data.password);

    this.data = "{\"userName\":\"" + data.username + "\","
             + " \"password\":\"" + data.password + "\","
             + " \"firstName\":\"" + data.firstname + "\"," 
             + " \"lastName\":\"" + data.lastname + "\"," 
             + " \"version\":0,"                    
             + " \"userType\":\"USER\"}";
    
    this.dataService.register(this.data).subscribe((response: boolean) => 
      {this.registered = response;
      if(response==true) 
        this.router.navigateByUrl('/login');
      });
  }
}
