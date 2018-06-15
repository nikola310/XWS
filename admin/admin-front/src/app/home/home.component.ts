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
  goals = [];

  constructor(private _data: DataService) { 
  }

  ngOnInit() {
    this._data.goal.subscribe(res => this.goals = res);
    this.itemCount = this.goals.length;
    this._data.changeGoal(this.goals);
  }

  addItem(){
    this.goals.push(this.goalText);
    this.goalText = '';
    this.itemCount = this.goals.length;
    this._data.changeGoal(this.goals);
  }

  removeItem(i){
    this.goals.splice(i, 1);
    this._data.changeGoal(this.goals);
  }
}
