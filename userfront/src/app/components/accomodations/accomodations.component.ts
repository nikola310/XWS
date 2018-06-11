import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-accomodations',
  templateUrl: './accomodations.component.html',
  styleUrls: ['./accomodations.component.css']
})
export class AccomodationsComponent implements OnInit {

  public accomodations = [];

  constructor(private dataService:DataService) { }

  ngOnInit() {
    this.dataService.getAllAccomodations()
        .subscribe(data => this.accomodations = data);
  }
}
