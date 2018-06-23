import { Component, OnInit } from '@angular/core';
import { IPrice } from '../../interfaces/price';
import { IBonusService } from '../../interfaces/bservice';
@Component({
  selector: 'app-newaccomodation',
  templateUrl: './newaccomodation.component.html',
  styleUrls: ['./newaccomodation.component.css']
})
export class NewaccomodationComponent implements OnInit {

  prices:IPrice[] = [{"priceStartDate":new Date().valueOf(), "priceEndDate":new Date().valueOf(), "price":0}];
  bonusServices:IBonusService[] = [{"id":1,"name":"WiFI"}, {"id":2, "name":"Parking"}];
  accomodationTypes = [];

  constructor() { }

  ngOnInit() {
    // this._dataService.getAllBonusServices()
    //     .subscribe(data => this.bonusServices = data);
   
    // this._dataService.getAllAccomodationTypes()
    //     .subscribe(data => this.accomodationTypes = data);
  }

  addNewPrice(){
    this.prices.push({"priceStartDate":new Date().valueOf(), "priceEndDate":new Date().valueOf(), "price":0});
  }
  addAccomodation(accomodation){
    console.log(accomodation);
    for(var i = 0; i < this.prices.length; i++){
      this.prices[i].priceStartDate= new Date(this.prices[i].priceStartDate).valueOf();
      this.prices[i].priceEndDate = new Date(this.prices[i].priceEndDate).valueOf();
      console.log("cijena " + this.prices[i].price);
      console.log("start "+ this.prices[i].priceStartDate);
      console.log("end "+ this.prices[i].priceEndDate)

    }
  }

  removePrice(i){
    this.prices.splice(i,1);
  }
}
