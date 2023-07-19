import { Component } from '@angular/core';
import {UserService} from "../service/user.service";
import {LogService} from "../service/log.service";

@Component({
  selector: 'app-filter-logs',
  templateUrl: './filter-logs.component.html',
  styleUrls: ['./filter-logs.component.css']
})
export class FilterLogsComponent {
  logs: Array<string[]> | undefined;
  pageCount: number | undefined = 1;
  page = 0;
  showNext = false;
  showPrevious = false;

  constructor(private userService: UserService, private logService: LogService) {
  }

  ngOnInit(): void {
    console.log('ngOnInit called for filterPage');
    // this.getAllLogs();
  }

  async loadTable(type: string, severity: string, page = 1): Promise<void> {
    this.pageCount = await this.logService.getCountFilterLogs(type, severity,4).toPromise();
    console.log("page count"+this.pageCount);
    this.logService.filterLogs(type, severity, 4, page)
      .subscribe(filteredLogs => this.logs = filteredLogs);
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


  goToPreviousPage(type: string, severity: string): void {
    if (this.page > 1) {
      this.loadTable(type, severity, this.page - 1);
    }
  }

  goToNextPage(type: string, severity: string): void {
    if (this.pageCount !== undefined && this.page < this.pageCount + 1) {
      this.loadTable(type, severity,this.page + 1);
    }
  }

}
