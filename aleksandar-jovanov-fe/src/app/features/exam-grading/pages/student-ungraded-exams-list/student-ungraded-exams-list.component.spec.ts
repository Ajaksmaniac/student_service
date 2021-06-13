import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentUngradedExamsListComponent } from './student-ungraded-exams-list.component';

describe('StudentUngradedExamsListComponent', () => {
  let component: StudentUngradedExamsListComponent;
  let fixture: ComponentFixture<StudentUngradedExamsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentUngradedExamsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentUngradedExamsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
