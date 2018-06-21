import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users.service';
import { UserInterface } from "../user-interface";
import { DataService } from '../data.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: UserInterface[];

  constructor(private userService: UsersService, private _data: DataService) { }

  ngOnInit() {
    this.userService.getUsers().subscribe(data => this.users = data);
    console.log(this.users);
  }

  activateUser(id: number, version: number) {
    this.userService.activate(id, version, sessionStorage.getItem('Token')).subscribe(data => console.log(data));
  }

  blockUser(id: number, version: number) {
    this.userService.block(id, version, sessionStorage.getItem('Token')).subscribe(data => console.log(data));
  }

  deleteUser(id: number, objectForRemoval: any) {
    this.userService.delete(id).subscribe(data => {
      if (data) {
        var i = this.users.indexOf(objectForRemoval);
        this.users.splice(i, 1);
      }
    });
  }

}
