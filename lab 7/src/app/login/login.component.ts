/*
import { Component, OnInit } from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {LogService} from '../service/log.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  loggedIn = false;
  userId = 100;
  constructor(private userService: UserService,
              private logService: LogService,
              private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.login(this.username, this.password).subscribe(
      response => {
        // Login successful, set logged in flag and redirect to home page
        this.loggedIn = true;
        window.location.href = '/homepage';
      },
      error => {
        // Login failed, display error message
        console.error(error);
      }
    );
  }
}
*/

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  angForm: FormGroup;

  constructor(private fb: FormBuilder, private dataService: UserService, private router: Router) {
    this.angForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(1)]],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  postdata(angForm1: { value: { username: any; password: any; }; }) {
    this.dataService.userlogin(angForm1.value.username, angForm1.value.password)
      .pipe(first())
      .subscribe(
        data => {
          const redirect = this.dataService.redirectUrl ? this.dataService.redirectUrl : '/homepage';
          this.router.navigate([redirect]);
        },
        error => {
          alert("User name or password is incorrect")
        });
  }

  get username() {
    return this.angForm.get('username');
  }

  get password() {
    return this.angForm.get('password');
  }
}
