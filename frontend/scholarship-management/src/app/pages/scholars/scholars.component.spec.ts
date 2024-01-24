import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ScholarsComponent } from './scholars.component';

describe('ScholarsComponent', () => {
  let component: ScholarsComponent;
  let fixture: ComponentFixture<ScholarsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ScholarsComponent]
    });
    fixture = TestBed.createComponent(ScholarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
