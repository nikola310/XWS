import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';
import { IAccomodation } from '../../interfaces/accomodation';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  private accomodations: IAccomodation[];
  private reservation;
  private searching: Boolean = false;

  private comments: Boolean[];
  private rating: number[];
  private resPrice: number;

  constructor(private dataService: DataService, private router: Router) {
    this.comments = [];
    this.accomodations = [];
    this.rating = [];
  }

  ngOnInit() {
    this.dataService.search()
      .subscribe(data => {
        this.accomodations = data;


        for (var i = 0; i < this.accomodations.length; i++) {
          this.accomodations[i].rating = 0;
          for (var j = 0; j < this.accomodations[i].comments.length; j++) {
            this.accomodations[i].rating += this.accomodations[i].comments[j].rating;
          }
          if (this.accomodations[i].rating != 0) {
            this.accomodations[i].rating = this.accomodations[i].rating / this.accomodations[i].comments.length;
          }
        }

      });
    for (var i = 0; i < this.accomodations.length; i++) {
      this.comments[i] = false;
    }




  }

  book(id) {
    for (var i = 0; i < this.accomodations.length; i++) {
      if (this.accomodations[i].id == id) {
        this.resPrice = this.accomodations[i].price;
        break;
      }
    }

    this.reservation = "{\"user\":" + this.dataService.getUserId() + ","
      + " \"accomodation\":" + id + ","
      + " \"numberOfPersons\":" + this.dataService.getGuests() + ","
      + " \"startDate\":" + this.dataService.getSearchCheckIn() + ","
      + " \"endDate\":" + this.dataService.getSearchCheckOut() + ","
      + " \"price\":" + this.resPrice + ","
      + " \"reviewed\":false,"
      + " \"version\":0,"
      + " \"realized\":false}";

    console.log(this.reservation);

    this.dataService.book(this.reservation).subscribe((response: boolean) => {
      if (response == true) {
        this.router.navigateByUrl('/reservations');
      }
    });
  }

  orderByPriceAscending() {

    for (var j = 0; j < this.accomodations.length - 1; j++) {
      for (var i = 0; i < this.accomodations.length - 1; i++) {
        if (this.accomodations[i].price > this.accomodations[i + 1].price) {
          var tempValue = this.accomodations[i];
          this.accomodations[i] = this.accomodations[i + 1];
          this.accomodations[i + 1] = tempValue;
        }
      }
    }
  }

  orderByPriceDescending() {

    this.orderByPriceAscending();
    this.accomodations.reverse();
  }


  orderByCategoryAscending() {
    for (var j = 0; j < this.accomodations.length - 1; j++) {
      for (var i = 0; i < this.accomodations.length - 1; i++) {
        if (this.accomodations[i].category > this.accomodations[i + 1].category) {
          var tempValue = this.accomodations[i];
          this.accomodations[i] = this.accomodations[i + 1];
          this.accomodations[i + 1] = tempValue;
        }
      }
    }
  }

  orderByCategoryDescending() {
    this.orderByCategoryAscending()
    this.accomodations.reverse();
  }

  orderByRatingAscending(){
    for (var j = 0; j < this.accomodations.length - 1; j++) {
      for (var i = 0; i < this.accomodations.length - 1; i++) {
        if (this.accomodations[i].rating > this.accomodations[i + 1].rating) {
          var tempValue = this.accomodations[i];
          this.accomodations[i] = this.accomodations[i + 1];
          this.accomodations[i + 1] = tempValue;
        }
      }
    }
  }
  

  orderByRatingDescending(){
    this.orderByRatingAscending()
    this.accomodations.reverse();
  }

  showComments(i) {
    if (this.comments[i] == true) {
      this.comments[i] = false;
    } else {
      this.comments[i] = true;
    }
    return this.comments[i];
  }

  show(i) {
    return this.comments[i];
  }

  getRating(i) {
    console.log("ratinggg" + this.rating[i]);
    return this.rating[i];
  }

}
