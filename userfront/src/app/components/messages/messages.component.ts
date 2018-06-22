import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { IMessage } from '../../interfaces/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  message;
  messages:IMessage[] = [];
  receiver;
  receiverUsername:String = "";

  constructor(private dataService:DataService, private router:Router) { }

  ngOnInit() {
    if(this.dataService.getLogged()!=true){
      this.router.navigateByUrl('/login');
    }
    this.dataService.getMyMessages().subscribe(res => this.messages = res);
  }

  sendMessage(data, receiver){
    console.log("data " + data.message);
    //console.log("kome saljem " + this.receiver.id);
    this.message = "{\"sender\":" + this.dataService.getUserId() + ","
    + " \"receiver\":" + this.receiver.id + ","
    + " \"content\":\"" + data.message + "\","
    + " \"version\":0}";
    console.log(this.message);

    this.dataService.sendMessage(this.message).subscribe(res => {
      console.log(res);
      if(res==true){
        this.ngOnInit();
      }
    });
   
  }

  setReceiver(receiver){
    console.log(receiver);
    this.receiver = receiver;
    this.receiverUsername = receiver.userName;
  }
}
