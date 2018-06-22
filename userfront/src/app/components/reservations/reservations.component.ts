import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  myReservations = [];
  today: number;
  data;
  reservationToUpdate;

  constructor(private dataService: DataService, private router: Router) {
    this.today = new Date().valueOf();

  }

  ngOnInit() {
    if (this.dataService.getLogged() == false) {
      this.router.navigateByUrl('/login');
    }
    this.dataService.getMyReservations().subscribe(data => this.myReservations = data);

   
  }

  cancelReservation(reservation) {
    this.dataService.cancelReservation(reservation).subscribe(res => {
      if (res == true) {
        this.ngOnInit();
      }
    });
  }

  leaveReview(data){
    console.log(data);
    console.log("akomodacija" + this.reservationToUpdate.accomodation.id);
    
     
    this.data = "{\"content\":\"" + data.comment + "\","
    + " \"rating\":" + data.rating + ","
    + " \"author\":" + this.dataService.getUserId() + "," 
    + " \"approved\":" + null + "," 
    + " \"version\":0,"                    
    + " \"accomodation\":" + this.reservationToUpdate.accomodation.id +"}";
    
    console.log(this.data);
    this.dataService.leaveReview(this.data).subscribe(res => console.log(res));

    var res = "{\"user\":" + this.dataService.getUserId() + ","
    + " \"id\":" + this.reservationToUpdate.id + ","
    + " \"accomodation\":" + this.reservationToUpdate.accomodation.id + ","
    + " \"numberOfPersons\":" + this.reservationToUpdate.numberOfPersons + ","
    + " \"startDate\":" + this.reservationToUpdate.startDate + ","
    + " \"endDate\":" + this.reservationToUpdate.endDate + ","
    + " \"price\":" + this.reservationToUpdate.price + ","
    + " \"realized\":" + this.reservationToUpdate.realized + ","
    + " \"reviewed\":true}";

    this.dataService.updateReservation(res).subscribe(response => {
      console.log(response);
      this.ngOnInit();
    });
    
  }

  setReservation(reservation){
    this.reservationToUpdate = reservation;
  }
}
