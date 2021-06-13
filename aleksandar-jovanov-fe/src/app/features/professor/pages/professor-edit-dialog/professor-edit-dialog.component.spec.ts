import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorEditDialogComponent } from './professor-edit-dialog.component';

describe('ProfessorEditDialogComponent', () => {
  let component: ProfessorEditDialogComponent;
  let fixture: ComponentFixture<ProfessorEditDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorEditDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
