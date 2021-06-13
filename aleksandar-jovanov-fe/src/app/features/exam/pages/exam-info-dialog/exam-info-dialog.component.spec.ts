import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamInfoDialogComponent } from './exam-info-dialog.component';

describe('ExamInfoDialogComponent', () => {
  let component: ExamInfoDialogComponent;
  let fixture: ComponentFixture<ExamInfoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamInfoDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
