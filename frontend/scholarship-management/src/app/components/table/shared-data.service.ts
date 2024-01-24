import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TableService } from './table.service';

@Injectable({
  providedIn: 'root',
})
export class SharedDataService {
  private formDataSubject = new BehaviorSubject<any>(null);
  formData$ = this.formDataSubject.asObservable();

  private id: any;

  private data: any;

  constructor( private tableService: TableService) {}

  setFormData(id: any) {
    this.id = id;
    if (id) {
      this.tableService.getScholarById(id).subscribe((scholarResponse) => {
        this.data = scholarResponse;
         this.formDataSubject.next(this.data);
      });
    } else {
      this.formDataSubject.next(this.data);
    }
  }

  getData() {
    return this.data;
  }

}
