import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users.service';
import { UserInterface } from "../user-interface";
import { Observable } from "rxjs/Rx";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  constructor(private userService: UsersService) { }

  private baseUrl = "localhost:8089/booking";
  private foods;
  private products = [];
  users: UserInterface[];

  ngOnInit() {
    this.userService.getUsers().subscribe(data => this.users = data);
    console.log(this.users);
  }

  activateUser(id: number, version: number){
    this.userService.activate(id, version).subscribe(data => console.log(data));
  }

  blockUser(id: number, version: number){
    this.userService.block(id, version).subscribe(data => console.log(data));
  }

  deleteUser(id: number){
    this.userService.delete(id).subscribe(data => console.log(data));
  }

}
