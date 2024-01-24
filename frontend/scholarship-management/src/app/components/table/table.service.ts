import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TableService {
  private readonly API = 'http://localhost:8080/scholars';

  constructor(
    private httpClient: HttpClient,
    ) {}

  listScholar(): Observable<any> {
    const url = `${this.API}/list`;
    return this.httpClient.get<any>(url);
  }

  getScholarById(id:number): Observable<any> {
    const url = `${this.API}/${id}`
    return this.httpClient.get<any>(url)
  }

  deleteScholar(id:number): Observable<string> {
    const url = `${this.API}/${id}`
    return this.httpClient.delete(url , { responseType: 'text' });
  }
}
