import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorInfoDialogComponent } from './professor-info-dialog.component';

describe('ProfessorInfoDialogComponent', () => {
  let component: ProfessorInfoDialogComponent;
  let fixture: ComponentFixture<ProfessorInfoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorInfoDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
