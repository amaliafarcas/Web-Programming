import { Component } from '@angular/core';
import {UserService} from "../service/user.service";
import {LogService} from "../service/log.service";

@Component({
  selector: 'app-my-logs',
  templateUrl: './my-logs.component.html',
  styleUrls: ['./my-logs.component.css']
})
export class MyLogsComponent {

  logs: { severity: string; index: string; id: string; date_of_log: string; type: string; message: string }[] = [];
  allLogs: Array<string[]> = [];
  // pageCount = Math.ceil(this.allLogs.length / 4);
  pageCount: number | undefined = 1;
  page = 0;
  showNext = false;
  showPrevious = false;

  constructor(private userService: UserService, private logService: LogService) { }

  async ngOnInit(): Promise<void> {
    console.log('ngOnInit called for myLogs');
    console.log('user id: ' + this.userService.getToken());
    this.pageCount = await this.logService.getCountMyLogs(4, parseInt(this.userService.getToken() || '')).toPromise();
    console.log('pages: ' + this.pageCount);
    this.loadTable();
    //this.getAllLogs();
    // this.getPageLogs();
  }

  loadTable(page = 1): void {
    this.logService.getMyLogs(4, page, parseInt(this.userService.getToken() || '')).subscribe(logsRes => {
      this.logs = logsRes.map((log) => {
        return {
          id: log[5], // assuming that the id is the 6th element in the array
          index: log[0],
          type: log[1],
          severity: log[2],
          date_of_log: log[3],
          message: log[4],
        };
      });
    });
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

  deleteLog(logId: string): void {
    this.logService.deleteLog(logId).subscribe(
      async () => {
        // Reload the table
        this.pageCount = await this.logService.getCountMyLogs(4, parseInt(this.userService.getToken() || '')).toPromise();
        this.loadTable(1);
        // Show alert message
        alert('Log deleted successfully.');
      },
      (error) => {
        alert(error);
      }
    );
  }
}
