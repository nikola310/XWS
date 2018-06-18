import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-codebook',
  templateUrl: './codebook.component.html',
  styleUrls: ['./codebook.component.scss']
})
export class CodebookComponent implements OnInit {

  constructor(private _data: DataService) { }

  ngOnInit() {
  }

}
