import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {RouterOutlet} from "@angular/router";
import {LogService} from "./service/log.service";
import {UserService} from "./service/user.service";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import { HomepageComponent } from './homepage/homepage.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { MyLogsComponent } from './my-logs/my-logs.component';
import { AddLogComponent } from './add-log/add-log.component';
import { FilterLogsComponent } from './filter-logs/filter-logs.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    MyLogsComponent,
    AddLogComponent,
    FilterLogsComponent
  ],
  imports: [
    BrowserModule,
    RouterOutlet,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService, LogService],
  bootstrap: [AppComponent]
})
export class AppModule { }
