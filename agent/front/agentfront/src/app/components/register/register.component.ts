import { Component, OnInit } from '@angular/core';
import { RestclientService } from '../../services/restclient.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userData: String;
  locationData: String;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  register(data){
    console.log(data);
    this.locationData =  "{\"state\":\"" + data.state + "\"," 
    + " \"city\":\"" + data.city + "\","  
    + " \"streetName\":\"" + data.address + "\","  
    + " \"streetNumber\":\"" + data.addrnum + "\"}"  
    
    this.userData = "{\"userName\":\"" + data.username + "\","
             + " \"password\":\"" + data.password + "\","
             + " \"firstName\":\"" + data.firstname + "\"," 
             + " \"lastName\":\"" + data.lastname + "\","
             + " \"pid\":\"" + data.bid + "\"}" ;            
    console.log(this.userData);

    this.userService.register(this.userData, this.locationData);
  }

}
