import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  advancedSearch: boolean;
  minCheckInDate: string;
  s: string;
  str: string;
  constructor(private dataService:DataService) {
    this.advancedSearch = false;
    this.minCheckInDate = new Date().toJSON().split('T')[0];
  }

  ngOnInit() {
  }

  showAdvancedSearch() {
    if (this.advancedSearch == true) {
      this.advancedSearch = false;
    }
    else {
      this.advancedSearch = true;
    }
  }

  search(data) {
    console.log("searching ...");
    console.log(data);
   // console.log("destination:"+data.destination+",check-in+date:"+data.checkin+",check-out+date:"+data.checkout+",guests:"+data.guests);

    this.s = 'http://localhost:8089/booking/search?destination='+data.destination+'&checkin='+data.checkin+'&checkout='+data.checkout+'&guests='+data.guests;
    console.log(this.s);
    if(data.atype !=undefined){
      this.s = this.s +'&type='+data.atype;
    }
    if(data.acategory !=undefined){
      this.s = this.s+'&category='+data.acategory;
    }
   
    if(data.parking != undefined && data.parking != false){
      this.s = this.s+'&parking=true';
    }
    if(data.wifi != undefined && data.wifi != false){
      this.s = this.s+'&wifi=true';
    }
    if(data.breakfast != undefined && data.breakfast != false){
      this.s = this.s+'&breakfast=true';
    }
    if(data.fullboard != undefined && data.fullboard != false){
      this.s = this.s+'&fullboard=true';
    }
    if(data.halfboard != undefined && data.halfboard != false){
      this.s = this.s+'&halfboard=true';
    }
    if(data.tv != undefined && data.tv != false){
      this.s = this.s+'&tv=true';
    }
    if(data.kitchen != undefined && data.kitchen != false){
      this.s = this.s+'&kitchen=true';
    }
    if(data.bathroom != undefined && data.bathroom != false){
      this.s = this.s+'&bathroom=true';
    }

    this.str = this.s.replace(/\s+/g,'+');
    
    this.dataService.search(this.str);
  }

}
