import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { AccomodationService } from "../accomodation.service";
import { Accomodation } from "../accomodation";

@Component({
  selector: 'app-accomodation-type',
  templateUrl: './accomodation-type.component.html',
  styleUrls: ['./accomodation-type.component.scss']
})
export class AccomodationTypeComponent implements OnInit {

  accomodations: Accomodation[];

  constructor(private accService: AccomodationService) { }

  ngOnInit() {
    this.accService.getAccTypes().subscribe(data => this.accomodations = data);
  }

  public editAccType(id: number, accName: string, version: number){
    var data = "{ \"id\":\"" + id + "\", \"name\":\"" + accName + "\", \"version\":\"" + version +"\"}";

    this.accService.edit(data).subscribe(response => {

      if(response != null){
        this.accService.getAccTypes().subscribe(data => this.accomodations = data);
      }
    });
  }

  public deleteAccType(id: number){
    this.accService.delete(id).subscribe(response => {

      if(response){
        this.accService.getAccTypes().subscribe(data => this.accomodations = data);
      }
    });
  }

  public createNewAccType(name: string){
    this.accService.newAccType(name).subscribe(response => {
      if(response){
        this.accService.getAccTypes().subscribe(data => this.accomodations = data);
        
      }else{
        window.alert('Error occured.');
      }
    });
  }
}
