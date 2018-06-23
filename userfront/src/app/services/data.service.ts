import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IPost } from '../interfaces/post';
import { Observable } from 'rxjs/Observable';
import { IAccomodation } from '../interfaces/accomodation';
import { IBonusService } from '../interfaces/bonusservice';
import { IAccomodationType } from '../interfaces/accomodationtype';
import { ILoginResponse } from '../interfaces/loginresponse';
import 'rxjs/add/operator/map';
import { IReservation } from '../interfaces/reservation';
import { IPicture } from '../interfaces/picture';
import { IMessage } from '../interfaces/message';

@Injectable()
export class DataService {

  private url: string;
  private token: string;
  private loginResponse: Observable<ILoginResponse>;
  private logged: Boolean= false;
  private username;
  private userId;

  private searchCheckIn;
  private searchCheckOut;
  private guests;
  private searchPrice;

  constructor(private http: HttpClient) { 
    this.url = "";
  }

  search():Observable<IAccomodation[]> {
    return this.http.get<IAccomodation[]>(this.url);
  }

  getAllAccomodations(): Observable<IAccomodation[]> {
    return this.http.get<IAccomodation[]>('http://localhost:8089/booking/accomodations');
  }

  getAllBonusServices(): Observable<IBonusService[]> {
    return this.http.get<IBonusService[]>('http://localhost:8089/booking/bonusservices');
  }

  getAllAccomodationTypes(): Observable<IAccomodationType[]>{
    return this.http.get<IAccomodationType[]>('http://localhost:8089/booking/types');
  }

  getPicturesByAccomodation(id): Observable<IPicture[]>{
    return this.http.get<IPicture[]>('http://localhost:8089/booking/picture/accomodation/'+id);
  }

  setUrl(url){
    this.url = url;
  }

  register(userData){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post('http://localhost:8089/booking/register', userData, config);
  }

  login(userData){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post<ILoginResponse>('http://localhost:8089/booking/login', userData, config);
  
  }

  setUsername(username){
    this.username = username;
  }

  getUsername(){
    return this.username;
  }
  
  setUserId(id){
    this.userId = id;
  }
  getUserId(){
    return this.userId;
  }
  setToken(token){
    this.token = token;
  }

  setLogged(logged){
    this.logged = logged;
  }

  getLogged(){   
    return this.logged;
  }

  setSearchCheckIn(checkin): void{
    this.searchCheckIn = checkin;
  }
  getSearchCheckIn(): number{
    return this.searchCheckIn;
  }

  setSearchCheckOut(checkout): void{
    this.searchCheckOut = checkout;
  }
  getSearchCheckOut(): number{
    return this.searchCheckOut;
  }

  setGuests(guests): void{
    this.guests = guests;
  }

  getGuests():number{
    return this.guests;
  }
  setSearchPrice(price){
    this.searchPrice = price;
  }
  getSearchPrice(){
    return this.searchPrice;
  }
  book(data){
    var config = {
      headers: {
        'content-type': 'application/json'
      }
    }
    console.log("uso u servis");
    return this.http.post('http://localhost:8089/booking/reservation', data, config);
  }

  getMyReservations(): Observable<IReservation[]>{
    console.log('http://localhost:8089/booking/reservation/myreservations/'+ this.userId);
    return this.http.get<IReservation[]>('http://localhost:8089/booking/reservation/myreservations/'+ this.userId);
  }

  cancelReservation(reservation){
    return this.http.delete('http://localhost:8089/booking/reservation/'+reservation);
  }

  leaveReview(data){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    return this.http.post('http://localhost:8089/booking/comment', data, config);
  }

  updateReservation(data){
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json' 
      }
    }
    console.log("uso u update");
    console.log(data);
    return this.http.put('http://localhost:8089/booking/reservation', data, config);
  }

  getMyMessages():Observable<IMessage[]>{
    return this.http.get<IMessage[]>('http://localhost:8089/booking/message/inbox/'+this.userId);
  }

  sendMessage(messageData){
    var config = {
      headers: {
        'content-type': 'application/json'
      }
    }
    return this.http.post('http://localhost:8089/booking/message', messageData, config);
  }

  setAccomodationAgentReceiver(accId){
    return this.http.get('http://localhost:8089/booking/'+accId+'/agent');
  }
}
