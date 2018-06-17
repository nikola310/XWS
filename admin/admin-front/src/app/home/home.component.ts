import { Component, OnInit, Injectable } from '@angular/core';
import { DataService } from '../data.service';
import { Data } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  itemCount: number;
  btnText: string = 'Add an item';
  goalText: string = 'My first life goal';

  constructor(private _data: DataService) { 
  }

  ngOnInit() {

  }

  addItem(){
   
  }

  removeItem(i){
   
  }
}
