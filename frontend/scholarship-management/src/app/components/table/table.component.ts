import { TableService } from './table.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalAddScholarComponent } from '../modal-add-scholar/modal-add-scholar.component';
import { SharedDataService } from './shared-data.service';
import { Scholar } from '../entities/scholar';
import { DataService } from '../util/data.service';
import { Subscription } from 'rxjs';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements  OnInit, OnDestroy {
  listScholar: Scholar[] = [];
  private subscription: Subscription = new Subscription();

  constructor(
    private modalService: NgbModal,
    private tableService: TableService,
    private sharedDataService: SharedDataService,
    private dataService: DataService,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.populateScholarList();

    this.subscription = this.dataService.updateList$.subscribe(() => {
      this.updateList();
    });
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  populateScholarList() {
    this.tableService.listScholar().subscribe((scholarResponse) => {
      this.listScholar = scholarResponse;
    });
  }
  openModal(id?: number) {
    this.sharedDataService.setFormData(id);
    const modalRef = this.modalService.open(ModalAddScholarComponent);
  }

  getBankName(code: number): string {
    switch (code) {
      case 1:
        return 'Banco do Brasil';
      case 260:
        return 'Nubank';
      case 237:
        return 'Bradesco';
      case 104:
        return 'Caixa Econômica';
      case 341:
        return 'Itau';
      default:
        return 'Banco Desconhecido';
    }
  }

  deleteScholar(id: any): any {
    if (id) {
      this.tableService.deleteScholar(id).subscribe(
        (response: string) => {
          console.log(response);
          this.ngOnDestroy();
          this.ngOnInit();
        },
        (error: any) => {
          console.error('Erro ao deletar estudante:', error);
        }
      );
    }
  }

  formatDate(date: Date): string {
    return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm:ss') || '';
  }

  updateList() {
    this.populateScholarList();
  }
}
