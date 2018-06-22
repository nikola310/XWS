import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private dataService:DataService) { 
  }

  ngOnInit() {
    
  }

  logOut(){
    this.dataService.setLogged(false);
    this.dataService.setToken("");
  }

}
