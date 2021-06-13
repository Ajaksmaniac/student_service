import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentExamHistoryListComponent } from './student-exam-history-list.component';

describe('StudentExamHistoryListComponent', () => {
  let component: StudentExamHistoryListComponent;
  let fixture: ComponentFixture<StudentExamHistoryListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentExamHistoryListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentExamHistoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
