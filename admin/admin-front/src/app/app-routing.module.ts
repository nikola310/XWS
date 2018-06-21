import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { AgentsComponent } from "./agents/agents.component";
import { CommentsComponent } from "./comments/comments.component";
import { AccomodationTypeComponent } from "./accomodation-type/accomodation-type.component";

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'acc-type',
    component: AccomodationTypeComponent
  },
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'agents',
    component: AgentsComponent
  },
  {
    path: 'comments',
    component: CommentsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
