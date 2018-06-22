import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SearchComponent } from './components/search/search.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ResultsComponent } from './components/results/results.component';
import { DataService } from './services/data.service';
import { AccomodationsComponent } from './components/accomodations/accomodations.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { MessagesComponent } from './components/messages/messages.component';


const appRoutes: Routes = [
  { path: '', component: SearchComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'results', component: ResultsComponent },
  { path: 'accomodations', component: AccomodationsComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'reservations', component: ReservationsComponent},
  { path: 'messages', component: MessagesComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SearchComponent,
    LoginComponent,
    RegisterComponent,
    ResultsComponent,
    AccomodationsComponent,
    ProfileComponent,
    ReservationsComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
