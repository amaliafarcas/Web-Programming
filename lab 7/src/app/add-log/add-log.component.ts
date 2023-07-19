import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";
import {first} from "rxjs/operators";
import {LogService} from "../service/log.service";

@Component({
  selector: 'app-add-log',
  templateUrl: './add-log.component.html',
  styleUrls: ['./add-log.component.css']
})
export class AddLogComponent {
  logForm: FormGroup;

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private logService: LogService,
              private router: Router) {

    this.logForm = this.fb.group({
      type: ['', [Validators.required, Validators.minLength(1)]],
      severity: ['', Validators.required],
      date: ['', Validators.required],
      message: ['', Validators.required],

    });
  }

  ngOnInit() {
  }

  postLog(logForm: { value: { type: any; severity: any; date: any; message: any;}; }) {
    this.logService.addLog(logForm.value.type, logForm.value.severity, logForm.value.date, this.userService.getToken() || '', logForm.value.message)
      .pipe(first())
      .subscribe(
        data => {
          const redirect = this.userService.redirectUrl ? this.userService.redirectUrl : '/myLogs';
          this.router.navigate([redirect]);
        },
        error => {
          alert("Log could not be added")
        });
  }

  get severity() {
    return this.logForm.get('severity');
  }

  get type() {
    return this.logForm.get('type');
  }

  get date() {
    return this.logForm.get('date');
  }

  get message() {
    return this.logForm.get('message');
  }
}
