import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { BonusService } from "../bonus.service";
import { BonusInterface } from "../bonus-interface";

@Component({
  selector: 'app-bonus-types',
  templateUrl: './bonus-types.component.html',
  styleUrls: ['./bonus-types.component.scss']
})
export class BonusTypesComponent implements OnInit {

  bonus: BonusInterface[];

  constructor(private bonusService: BonusService) { }

  ngOnInit() {
    this.bonusService.getBonus().subscribe(response => this.bonus = response);
  }

  editBonus(id: number, name: string, version: number){
    var dto = "{ \"id\":" + id + ", \"name\":\"" + name + "\", \"version\":" + version +"}";
    
    this.bonusService.editBonus(dto).subscribe(response => {
      if(response){
        this.bonusService.getBonus().subscribe(response => this.bonus = response);
      }else{
        window.alert('Error occurred.');
      }
    });
  }

  deleteBonus(id: number){
    this.bonusService.delete(id).subscribe(response => {
      if(response){
        this.bonusService.getBonus().subscribe(response => this.bonus = response);
      }else{
        window.alert('Error occurred.');
      }
    });
  }

  newBonus(name: string){
    this.bonusService.createBonus(name).subscribe(response => {
      if(response){
        this.bonusService.getBonus().subscribe(response => this.bonus = response);
      }else{
        window.alert('Error occurred.');
      }
    });
  }
}
