import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentEditExamDialogComponent } from './student-edit-exam-dialog.component';

describe('StudentEditExamDialogComponent', () => {
  let component: StudentEditExamDialogComponent;
  let fixture: ComponentFixture<StudentEditExamDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentEditExamDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentEditExamDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
