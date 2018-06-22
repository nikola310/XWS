import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { AgentsComponent } from "./agents/agents.component";
import { CommentsComponent } from "./comments/comments.component";
import { AccomodationTypeComponent } from "./accomodation-type/accomodation-type.component";
import { AccCategoryComponent } from "./acc-category/acc-category.component";
import { BonusTypesComponent } from "./bonus-types/bonus-types.component";

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
  },
  {
    path: 'acc-category',
    component: AccCategoryComponent
  },{
    path: 'bonus-types',
    component: BonusTypesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
