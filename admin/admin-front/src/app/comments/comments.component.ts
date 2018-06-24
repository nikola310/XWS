import { Component, OnInit } from '@angular/core';
import { CommentService } from "../comment.service";
import { CommentInterface } from "../comment-interface";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent implements OnInit {

  private msg;

  comments: CommentInterface[];

  constructor(private commentService: CommentService) { }

  ngOnInit() {
    this.commentService.getComments().subscribe(data => this.comments = data);
    console.log(this.comments);
  }

  approveComment(id: number, version: number, toRemove: any): void {
    console.log(id);
    console.log(version);
    this.commentService.manageComment(id, version, true, sessionStorage.getItem('Token')).subscribe(data => {
      console.log(data)
      if (data) {
        window.alert('Accepted.');
        var i = this.comments.indexOf(toRemove);
        this.comments.splice(i, 1);
      }else{
        window.alert('Error occurred.');
      }
    });
  }

  rejectComment(id: number, version: number, toRemove: any): void {
    this.commentService.manageComment(id, version, false, sessionStorage.getItem('Token')).subscribe(data => {
      console.log(data)
      if (data) {
        window.alert('Comment rejected.');
        var i = this.comments.indexOf(toRemove);
        this.comments.splice(i, 1);
      }else{
        window.alert('Error occurred.');
      }
    });
  }
  
}
