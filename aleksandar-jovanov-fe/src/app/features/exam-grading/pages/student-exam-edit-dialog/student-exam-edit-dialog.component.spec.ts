import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentExamEditDialogComponent } from './student-exam-edit-dialog.component';

describe('StudentExamEditDialogComponent', () => {
  let component: StudentExamEditDialogComponent;
  let fixture: ComponentFixture<StudentExamEditDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentExamEditDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentExamEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
