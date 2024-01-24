import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddScholarComponent } from './modal-add-scholar.component';

describe('ModalAddScholarComponent', () => {
  let component: ModalAddScholarComponent;
  let fixture: ComponentFixture<ModalAddScholarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalAddScholarComponent]
    });
    fixture = TestBed.createComponent(ModalAddScholarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
