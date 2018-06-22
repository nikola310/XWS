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
  maxCheckInDate: string;
  minCheckOutDate: string;
  minCheckOutDateIfCheckIn: string;
  checkIn: Date;
  checkOut: Date;
  today: Date;
  tomorrow: Date;
  bonusServices = [];
  accomodationTypes = [];
  url: string;
  urlNoSpaces: string;

  constructor(private _dataService:DataService) {
    this.advancedSearch = false;
    this.minCheckInDate = new Date().toJSON().split('T')[0];
    this.today = new Date();
    this.tomorrow = new Date();
    this.tomorrow.setDate(this.today.getDate() +1);
    this.minCheckOutDate = this.tomorrow.toJSON().split('T')[0];
  }

  ngOnInit() {
    this._dataService.getAllBonusServices()
        .subscribe(data => this.bonusServices = data);
   
    this._dataService.getAllAccomodationTypes()
        .subscribe(data => this.accomodationTypes = data);
  }

  showAdvancedSearch() {
    if (this.advancedSearch == true) {
      this.advancedSearch = false;
    }
    else {
      this.advancedSearch = true;
    }
  }

  minDate(checkInDate){ 
    if(checkInDate != null && checkInDate != undefined){
       var comp = checkInDate.split('-');
       var date = new Date(comp[0], comp[1]-1, comp[2]);    
       date.setUTCDate(date.getDate() + 1); 
       this.minCheckOutDateIfCheckIn = date.toJSON().split('T')[0];      
    }    
  }

  maxDate(checkOutDate){
    if(checkOutDate != null && checkOutDate != undefined){
        var comp = checkOutDate.split('-');
        var date = new Date(comp[0], comp[1]-1, comp[2]);    
        date.setUTCDate(date.getDate() -1); 
        this.maxCheckInDate = date.toJSON().split('T')[0];     
    }
  }

  search(data) {
    console.log("searching ...");
    console.log(data);
  
    this.checkIn = new Date(data.checkin);
    this.checkOut = new Date(data.checkout);

    this._dataService.setSearchCheckIn(this.checkIn.valueOf());
    this._dataService.setSearchCheckOut(this.checkOut.valueOf());
    this._dataService.setGuests(data.guests);
    
    this.url = 'http://localhost:8089/booking/search?destination='+data.destination+'&checkin='+this.checkIn.valueOf()+'&checkout='+this.checkOut.valueOf()+'&guests='+data.guests;
    

    if(data.type !=undefined && data.type != ""){
      this.url = this.url +'&type='+data.type;
    }
    if(data.category != undefined){
      this.url = this.url+'&category='+data.category;
    }
   
    for(var i = 0; i < this.bonusServices.length; i++){
      if(i == 0){
        if(data[this.bonusServices[i].name] != undefined && (data[this.bonusServices[i].name] != false)){
          this.url = this.url+'&bonus='+this.bonusServices[i].id;
        }
      }else{
        if(data[this.bonusServices[i].name] != undefined && (data[this.bonusServices[i].name] != false)){
          this.url = this.url+','+this.bonusServices[i].id;
        }
      }
    } 

    this.urlNoSpaces = this.url.replace(/\s+/g,'+');
    console.log(this.urlNoSpaces);
    this._dataService.setUrl(this.urlNoSpaces);
  }

}
