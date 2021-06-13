import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectInfoDialogComponent } from './subject-info-dialog.component';

describe('SubjectInfoDialogComponent', () => {
  let component: SubjectInfoDialogComponent;
  let fixture: ComponentFixture<SubjectInfoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubjectInfoDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubjectInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
