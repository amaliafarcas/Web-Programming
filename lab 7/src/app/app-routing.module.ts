import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
//import {HomepageComponent} from './homepage/homepage.component';
import {LoginComponent} from './login/login.component';
import {HomepageComponent} from "./homepage/homepage.component";
import {MyLogsComponent} from "./my-logs/my-logs.component";
import {AddLogComponent} from "./add-log/add-log.component";
import {FilterLogsComponent} from "./filter-logs/filter-logs.component";
//import {AddPageComponent} from './add-page/add-page.component';
//import {DeletePageComponent} from './delete-page/delete-page.component';
//import {UpdatePageComponent} from './update-page/update-page.component';
//import {FilterPageComponent} from './filter-page/filter-page.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'homepage', component: HomepageComponent},
 { path: 'myLogs', component: MyLogsComponent},
  { path: 'addLog', component: AddLogComponent},
   { path: 'filterLogs', component: FilterLogsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
