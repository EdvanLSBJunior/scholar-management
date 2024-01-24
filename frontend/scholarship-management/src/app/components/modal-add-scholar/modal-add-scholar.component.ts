import { SharedDataService } from '../table/shared-data.service';
import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, Validators } from '@angular/forms';
import { ModalAddScholarService } from './modal-add-scholar.service';
import { DataService } from '../util/data.service';

@Component({
  selector: 'app-modal-add-scholar',
  templateUrl: './modal-add-scholar.component.html',
  styleUrls: ['./modal-add-scholar.component.css'],
})
export class ModalAddScholarComponent implements OnInit {
  formData: any = [];

  uniqueScholar: any = [];

  documentType: any = [
    { value: 'CPF', label: 'CPF' },
    { value: 'CNH', label: 'CNH' },
    { value: 'RG', label: 'RG' },
  ];
  bankName: any = [
    { value: 1, label: 'Banco do Brasil' },
    { value: 260, label: 'Nubank' },
    { value: 237, label: 'Bradesco' },
    { value: 104, label: 'Caixa Economica' },
    { value: 341, label: 'Itau' },
  ];

  constructor(
    private activeModal: NgbActiveModal,
    private sharedDataService: SharedDataService,
    private formBuilder: FormBuilder,
    private modalService: ModalAddScholarService,
    private dataService: DataService,
  ) {
    this.formData = this.formBuilder.group({
      id: [''],
      scholarName: ['', Validators.required],
      documentType: ['', Validators.required],
      codePayment: [0, Validators.required],
      agencyNumber: ['', Validators.required],
      documentNumber: ['', Validators.required],
      accountNumber: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.sharedDataService.formData$.subscribe((data) => {
      (this.uniqueScholar = data), this.startForm(data);
    });
  }

  startForm(formData: any) {
    this.formData = this.formBuilder.group({
      id: [formData?.id || ''],
      scholarName: [formData?.scholarName || '', Validators.required],
      documentType: [formData?.documentType || '', Validators.required],
      codePayment: [formData?.codePayment || 0, Validators.required],
      agencyNumber: [formData?.agencyNumber || '', Validators.required],
      documentNumber: [formData?.documentNumber || '', Validators.required],
      accountNumber: [formData?.accountNumber || '', Validators.required],
    });
  }

  onSelectChange(event: any) {
    const selectedValue = event.target.value;
    this.formData.patchValue({ documentType: selectedValue });
  }

  onSelectChangeBank(event: any) {
    const selectedValue = event.target.value;
    this.formData.patchValue({ codePayment: selectedValue });
  }

  createScholar() {
    this.modalService.createScholar(this.formData).subscribe(() => {});
    this.dataService.triggerListUpdate();
  }

  saveScholar(uniqueScholar: any) {
    if (uniqueScholar) {
      this.editScholar();
    } else {
      this.createScholar();
    }
    this.closeModal();
    this.dataService.triggerListUpdate();
  }

  editScholar() {
    this.modalService.editScholar(this.formData).subscribe(() => {});
    this.dataService.triggerListUpdate();
  }

  closeModal() {
    this.activeModal.close();
  }
}
