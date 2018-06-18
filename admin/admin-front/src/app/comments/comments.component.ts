import { Component, OnInit } from '@angular/core';
import { CommentService } from "../comment.service";
import { CommentInterface } from "../comment-interface";
import { DataService } from '../data.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent implements OnInit {

	private msg;

	comments: CommentInterface[];

  constructor(private commentService: CommentService, private _data: DataService) { }

  ngOnInit() {
		this.commentService.getComments().subscribe(data => this.comments = data);
		console.log(this.comments);
  }
  
  approveComment(id : number, version: number) : void {
	  this.commentService.manageComment(id, version, true, this._data.getToken()).subscribe(data => console.log(data));
  }

  rejectComment(id : number, version: number) : void {
	  this.commentService.manageComment(id, version, false, this._data.getToken()).subscribe(data => console.log(data));
  }
}
