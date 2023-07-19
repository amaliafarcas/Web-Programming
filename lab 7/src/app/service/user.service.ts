/*
import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {User} from '../model/user';
import {map, Observable, of, tap} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost/wp/lab7/php/login.php';
  //private getUsersURL = 'http://localhost/lab/Lab8/PHP/getUsers.php';
  userId = 100;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

/!*  logIn(username: string, password: string): Observable<string> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('username', username)
      .set('password', password);
    return this.http.post(this.userUrl, null, {headers, params, responseType: 'text'})
      .pipe(
        map(userId => JSON.parse(<string>userId)), // parse the JSON string
        tap(userId => sessionStorage.setItem('id', userId)), // store the user ID in the session
        catchError(this.handleError<string>('logIn', ''))
      );
  }*!/

  login(username: string, password: string) {
    const credentials = { username: username, password: password };
    return this.http.post(this.userUrl, credentials);
  }



  // tslint:disable-next-line:typedef
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

/!*  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.getUsersURL)
      .pipe(catchError(this.handleError<User[]>('fetchUsers', []))
      );
  }*!/
}
*/


import { Injectable, Output, EventEmitter } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  redirectUrl: string | undefined;
  loginURL:string = "http://localhost/wp/lab7/php/login.php";
  @Output() getLoggedInId: EventEmitter<any> = new EventEmitter();
  constructor(private httpClient : HttpClient) { }
  public userlogin(username: any, password: any) {
    //alert(username)
    return this.httpClient.post<any>(this.loginURL, { username, password })
      .pipe(map(Users => {
        this.setToken(Users[0].id);
        this.getLoggedInId.emit(true);
        return Users;
      }));
  }

//token
  setToken(token: number) {
    localStorage.setItem('token', String(token));
  }
  getToken() {
    return localStorage.getItem('token');
  }
  deleteToken() {
    localStorage.removeItem('token');
  }
  isLoggedIn() {
    const usertoken = this.getToken();
    if (usertoken != null) {
      return true
    }
    return false;
  }
}
