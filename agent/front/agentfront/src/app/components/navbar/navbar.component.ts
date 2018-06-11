import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logged:boolean = false;

  constructor(public userService:UserService) { }

  ngOnInit() { }

  onLogout() {
  	this.userService.logout();
    this.logged = false
  }

}
