import { TableService } from './../table/table.service';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Scholar } from "../entities/scholar";

@Injectable({
    providedIn: 'root',
  })
export class ModalAddScholarService {
    private readonly API = 'http://localhost:8080/scholars';

  constructor(
    private httpClient: HttpClient,
    private tableService: TableService
    ) {}

  createScholar(scholar: any): Observable<any> {
    const json = this.convertToJson(scholar);
    return this.httpClient.post(this.API, json , { responseType: 'text' });
  }

  editScholar(scholar: any): Observable<string> {
    const url = `${this.API}/${scholar.get('id').value}`
    const json = this.convertToJson(scholar);
    return this.httpClient.put(url, json , { responseType: 'text' })
  }

  convertToJson(scholar: any): Scholar {
    const formValues = scholar.value;

    console.log(scholar.value);
    
    const jsonString = JSON.stringify(formValues);
    const scholarDTO = JSON.parse(jsonString);
    return scholarDTO;
  }
  
}