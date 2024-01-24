import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private updateListSource = new Subject<void>();

  updateList$ = this.updateListSource.asObservable();

  triggerListUpdate() {
    this.updateListSource.next();
  }
}