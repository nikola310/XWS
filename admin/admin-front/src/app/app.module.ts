import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from "@angular/http";
import { HttpClientModule } from '@angular/common/http';

import { DataService } from './data.service';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { CommentsComponent } from './comments/comments.component';
import { AgentsComponent } from './agents/agents.component';
import { UsersComponent } from './users/users.component';
import { LoginComponent } from './login/login.component';
import { CodebookComponent } from './codebook/codebook.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    CommentsComponent,
    AgentsComponent,
    UsersComponent,
    LoginComponent,
    CodebookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule,
    HttpClientModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
