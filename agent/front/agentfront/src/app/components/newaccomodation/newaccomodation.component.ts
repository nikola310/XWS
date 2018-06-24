import { Component, OnInit } from '@angular/core';
import { IPrice } from '../../interfaces/price';
import { IBonusService } from '../../interfaces/bservice';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { RestclientService } from '../../services/restclient.service';
import { IAccomodationType } from '../../interfaces/accomodationtype';
@Component({
  selector: 'app-newaccomodation',
  templateUrl: './newaccomodation.component.html',
  styleUrls: ['./newaccomodation.component.css']
})
export class NewaccomodationComponent implements OnInit {

  imageSrc: string = '';
  prices: IPrice[] = [{ "priceStartDate": new Date().valueOf(), "priceEndDate": new Date().valueOf(), "price": null }];
  bonusServices: IBonusService[] = [{ "id": 1, "name": "WiFI" }, { "id": 2, "name": "Parking" }];
  accomodationTypes: IAccomodationType[] = [];
  locationId;

  newAccomodation: String;
  createdAccomodationId;
  newPrice;
  newPicture;
  accomodationAgent;

  constructor(private userService: UserService, private service: RestclientService, private router: Router) { }

  ngOnInit() {

    if (!this.userService.getLogged()) {
      this.router.navigateByUrl('/');
    }

    console.log(this.userService.getLogged());
    console.log(this.userService.getUserId());
    console.log(this.userService.getUsername());
    // this._dataService.getAllBonusServices()
    //     .subscribe(data => this.bonusServices = data);

    this.service.getAccomodationTypes()
      .subscribe(data => this.accomodationTypes = data);
  }

  addNewPrice() {
    this.prices.push({ "priceStartDate": new Date().valueOf(), "priceEndDate": new Date().valueOf(), "price": null });
  }
  addAccomodation(accomodation) {
    console.log(accomodation);
    for (var i = 0; i < this.prices.length; i++) {
      this.prices[i].priceStartDate = new Date(this.prices[i].priceStartDate).valueOf();
      this.prices[i].priceEndDate = new Date(this.prices[i].priceEndDate).valueOf();

    }

    let location = { 'state': accomodation.state, 'city': accomodation.city, 'streetName': accomodation.streetName, 'streetNumber': accomodation.streetNumber };
    console.log(location);
    this.service.addLocation(location).subscribe(res => {
      this.locationId = res;
      this.newAccomodation = "{\"name\":\"" + accomodation.name + "\","
        + " \"type\":" + accomodation.type + ","
        + " \"capacity\":" + accomodation.capacity + ","
        + " \"location\":" + this.locationId + "}";
      console.log(this.newAccomodation);
      this.service.addAccomodation(this.newAccomodation).subscribe(response => {
        console.log(response);
        this.createdAccomodationId = response;
        // for(var i = 0; i < this.bonusServices.length; i++){
        //   if(accomodation.this.bonusServices[i].name){
        //     var bonus = "{\"name\":\"" + accomodation.name + "\","
        //     + " \"type\":" + 1 + ","
        //     + " \"capacity\":" + accomodation.capacity + ","
        //     + " \"location\":" + this.locationId + "}";
        //     this.service.addAccomodationBonus
        //   }
        // }
        this.newPicture = "{\"accomodation\":" + this.createdAccomodationId + ","
        + " \"content\":\"" + this.imageSrc + "\"}";
        console.log(this.newPicture);
       this.service.addPicture(this.newPicture).subscribe(res=>console.log(res));

        this.accomodationAgent = "{\"accomodation\":" + this.createdAccomodationId + ","
        + " \"agent\":" + this.userService.getUserId() + ","
        + " \"mainAgent\":" + true + "}";
       
        console.log(this.accomodationAgent);
        this.service.setAccomodationAgent(this.accomodationAgent).subscribe(res=>console.log(res));

        for (var i = 0; i < this.prices.length; i++) {
          this.newPrice = "{\"price\":" + this.prices[i].price + ","
            + " \"startDate\":" + this.prices[i].priceStartDate + ","
            + " \"endDate\":" + this.prices[i].priceEndDate + ","
            + " \"accomodation\":" + this.createdAccomodationId + "}";
          this.service.addPrice(this.newPrice).subscribe(res => console.log(res));
        }
        this.router.navigateByUrl('/home');
      });
    });


  }

  removePrice(i) {
    this.prices.splice(i, 1);
  }



  handleInputChange(e) {
    var file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    var pattern = /image-*/;
    var reader = new FileReader();
    if (!file.type.match(pattern)) {
      alert('invalid format');
      return;
    }
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }
  _handleReaderLoaded(e) {
    let reader = e.target;
    this.imageSrc = reader.result;
    console.log(this.imageSrc)
  }
}
