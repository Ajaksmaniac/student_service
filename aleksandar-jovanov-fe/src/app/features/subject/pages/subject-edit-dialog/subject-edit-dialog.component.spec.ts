import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectEditDialogComponent } from './subject-edit-dialog.component';

describe('SubjectEditDialogComponent', () => {
  let component: SubjectEditDialogComponent;
  let fixture: ComponentFixture<SubjectEditDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubjectEditDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubjectEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
