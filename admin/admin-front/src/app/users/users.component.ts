import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users.service';
import { UserInterface } from "../user-interface";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: UserInterface[];

  constructor(private userService: UsersService) { }

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers(): any{
    this.userService.getUsers().subscribe(data => this.users = data);
  }

  activateUser(id: number, version: number) {
    this.userService.activate(id, version, sessionStorage.getItem('Token')).subscribe(data => {
      if(data){
        this.loadUsers();
      }else{
        window.alert('Error occurred.');
      }
    });
  }

  blockUser(id: number, version: number) {
    this.userService.block(id, version, sessionStorage.getItem('Token')).subscribe(data => {
      if(data){
        this.loadUsers();
      }else{
        window.alert('Error occurred.');
      }
    });
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
