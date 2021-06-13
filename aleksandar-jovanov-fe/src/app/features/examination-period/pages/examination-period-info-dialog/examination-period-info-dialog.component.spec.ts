import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationPeriodInfoDialogComponent } from './examination-period-info-dialog.component';

describe('ExaminationPeriodInfoDialogComponent', () => {
  let component: ExaminationPeriodInfoDialogComponent;
  let fixture: ComponentFixture<ExaminationPeriodInfoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationPeriodInfoDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationPeriodInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
