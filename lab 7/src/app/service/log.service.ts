import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Log} from '../model/log';
import {Observable, of, tap} from 'rxjs';
import {catchError, map} from 'rxjs/operators';

class Page {
  // tslint:disable-next-line:variable-name
  page_count = 0;
  // tslint:disable-next-line:variable-name
  page_content: Array<string[]> = [];
}

@Injectable({
  providedIn: 'root'
})
export class LogService {

  private getAllLogsURL = 'http://localhost/wp/lab7/php/getLogsPaginated.php';
  private getCountLogsURL = 'http://localhost/wp/lab7/php/getLogsCount.php';
  private getMyLogsURL = 'http://localhost/wp/lab7/php/getMyLogsPaginated.php';
  private getCountMyLogsURL = 'http://localhost/wp/lab7/php/getMyLogsCount.php';
  private addLogURL = 'http://localhost/wp/lab7/php/addLog.php';
  private deleteLogURL = 'http://localhost/wp/lab7/php/deleteLog.php';
  private getFilteredLogsURL = 'http://localhost/wp/lab7/php/filterLogs.php';
  private getCountFilterLogsURL = 'http://localhost/wp/lab7/php/filterLogsCount.php';

  userId = '1';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

  setUserId(id: string): void {
    this.userId = id;
  }

  getCountLogs(pageSize: number): Observable<number> {
    return this.http.get(this.getCountLogsURL + `?pageSize=${pageSize}`) as Observable<number>;
  }

  getCountMyLogs(pageSize: number, userId: number): Observable<number> {
    return this.http.get(this.getCountMyLogsURL + `?pageSize=${pageSize}&userId=${userId}`) as Observable<number>;
  }


  getAllLogs(pageSize: number, page: number): Observable<Array<string[]>> {
    /* body of the method */
    // @ts-ignore
    return this.http.get<Array<string[]>>(this.getAllLogsURL +
      `?pageSize=${pageSize}&page=${page}`);
  }

  getMyLogs(pageSize: number, page: number, userId: number): Observable<Array<string[]>> {
    /* body of the method */
    // @ts-ignore
    return this.http.get<Array<string[]>>(this.getMyLogsURL +
      `?pageSize=${pageSize}&page=${page}&userId=${userId}`);

  }

  filterLogs(type: string, severity: string, size: number, page: number): Observable<Array<string[]>> {
    return this.http.get<Array<string[]>>(this.getFilteredLogsURL +
      `?type=${type}` + `&severity=${severity}` + `&size=${size}` + `&page=${page}`);
  }

  getCountFilterLogs(type: string, severity: string, size: number): Observable<number> {
    return this.http.get(this.getCountFilterLogsURL +
      `?type=${type}` + `&severity=${severity}` + `&size=${size}`) as Observable<number>;
  }

  addLog(type: string, severity: string, date: string, userId: string, message: string): Observable<string> {
    return this.http.get(this.addLogURL + `?type=${type}` + `&severity=${severity}` +
      `&date=${date}` + `&userId=${userId}` + `&message=${message}`,
      {responseType: 'text'})
      .pipe(catchError(this.handleError<string>('addLog', '')));
  }

  deleteLog(logId: string): Observable<string> {
    return this.http.get(this.deleteLogURL + `?logId=${logId}`,
      {responseType: 'text'})
      .pipe(catchError(this.handleError<string>('deleteLog', '')));
  }

  // tslint:disable-next-line:typedef
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
