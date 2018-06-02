import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  advancedSearch: boolean = false;

  constructor() { }

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

  search(data){
    console.log("searching ...");
  }

}
