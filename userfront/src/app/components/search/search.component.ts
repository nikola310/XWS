import { Component, OnInit } from '@angular/core';

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
  constructor() {
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

    this.s = 'destination:'+data.destination+',check-in+date:'+data.checkin+',check-out+date:'+data.checkout+',guests:'+data.guests;
    console.log(this.s);
    if(data.atype !=undefined){
      this.s = 'destination:'+data.destination+',check-in+date:'+data.checkin+',check-out+date:'+data.checkout+',guests:'+data.guests+',atype:'+data.atype;
    }
    if(data.acategory !=undefined){
      this.s = this.s+',acategory:'+data.acategory;
    }
   

    if(data.parking != undefined && data.parking != false){
      this.s = this.s+',parking';
    }

    this.str = this.s.replace(/\s+/g,'+');
    
    console.log(this.str);
    
    console.log(data.parking);
    console.log(data.wifi);
  }

}
