import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from "@angular/http";
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommentsComponent } from './comments/comments.component';
import { AgentsComponent } from './agents/agents.component';
import { UsersComponent } from './users/users.component';
import { LoginComponent } from './login/login.component';
import { AccomodationTypeComponent } from './accomodation-type/accomodation-type.component';
import { AccCategoryComponent } from './acc-category/acc-category.component';
import { BonusTypesComponent } from './bonus-types/bonus-types.component';

@NgModule({
  declarations: [
    AppComponent,
    CommentsComponent,
    AgentsComponent,
    UsersComponent,
    LoginComponent,
    AccomodationTypeComponent,
    AccCategoryComponent,
    BonusTypesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
