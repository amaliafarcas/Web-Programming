import { Component, OnInit } from '@angular/core';
import {UserService} from '../service/user.service';
import {User} from '../model/user';
import {LogService} from '../service/log.service';
import {Log} from '../model/log';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  logs: Array<string[]> = [];
  pageCount: number | undefined = 1;
  page = 0;
  showNext = false;
  showPrevious = false;

  constructor(private userService: UserService, private logService: LogService) { }

  async ngOnInit(): Promise<void> {
    console.log('ngOnInit called for homepage');
    this.pageCount = await this.logService.getCountLogs(4).toPromise();
    console.log('pages: ' + this.pageCount);
    this.loadTable();
    //this.getAllLogs();
    // this.getPageLogs();
  }

/*  getCountLogs(): void {
    this.logService.getCountLogs(4)
      .subscribe(pageCount => this.pageCount = pageCount[0]);
  }*/

/*  getCountLogs(): void {
    this.logService.getCountLogs(4)
      .subscribe(pageCount => this.pageCount = pageCount);
  }*/

  loadTable(page = 1): void {
    this.logService.getAllLogs(4, page)
      .subscribe(logsRes => this.logs = logsRes);
    this.page = page;
    this.showNext = false;
    this.showPrevious = false;
    console.log('cp ' + this.page);
    console.log('tp ' + this.pageCount);
    if (this.pageCount !== undefined && this.pageCount > 0 && page < this.pageCount) {
      this.showNext = true;
    }
    if (page > 1) {
      this.showPrevious = true;
    }
  }


  goToPreviousPage(): void {
    if (this.page > 1) {
      this.loadTable(this.page - 1);
    }
  }

  goToNextPage(): void {
    if (this.pageCount !== undefined && this.page < this.pageCount + 1) {
      this.loadTable(this.page + 1);
    }
  }

}
