import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  public accomodations = [];
  
  constructor(private _dataService:DataService) { }

  ngOnInit() {
    this._dataService.search()
        .subscribe(data => this.accomodations = data);
  }

}
