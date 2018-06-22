import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { AccomodationService } from "../accomodation.service";
import { AccRank } from "../acc-rank";

@Component({
  selector: 'app-acc-category',
  templateUrl: './acc-category.component.html',
  styleUrls: ['./acc-category.component.scss']
})
export class AccCategoryComponent implements OnInit {

  accomodations: AccRank[];

  constructor(private accService: AccomodationService) { }

  ngOnInit() {
    this.accService.getAccomodations().subscribe(data => this.accomodations = data);
  }

  setCategory(id: number, value: any, version: number) {
    var dto = "{ \"id\":" + id + ", \"category\":" + parseInt(value) + ", \"version\":" + version +"}";
    this.accService.setRank(dto).subscribe(response => {
      if(response){
        this.accService.getAccomodations().subscribe(data => this.accomodations = data);
      }else{
        window.alert('Error occurred.');
      }
    });
  }

}
