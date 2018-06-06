import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  register(data) {
    console.log('registering ...');
    console.log('Your first name is ' + data.firstname);
    console.log('Your last name is ' + data.lastname);
    console.log('Your username is ' + data.username);
    console.log('Your password is ' + data.password);
    console.log('Your email is ' + data.email);
  }
}
