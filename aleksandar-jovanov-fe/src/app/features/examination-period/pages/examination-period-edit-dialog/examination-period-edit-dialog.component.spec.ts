import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationPeriodEditDialogComponent } from './examination-period-edit-dialog.component';

describe('ExaminationPeriodEditDialogComponent', () => {
  let component: ExaminationPeriodEditDialogComponent;
  let fixture: ComponentFixture<ExaminationPeriodEditDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationPeriodEditDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationPeriodEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
