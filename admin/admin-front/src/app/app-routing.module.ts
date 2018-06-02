import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { AgentsComponent } from "./agents/agents.component";
import { CommentsComponent } from "./comments/comments.component";
import { CodebookComponent } from "./codebook/codebook.component";

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'about/:id',
    component: AboutComponent
  },
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'agents',
    component: AgentsComponent
  },
  {
    path: 'codebook',
    component: CodebookComponent
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
