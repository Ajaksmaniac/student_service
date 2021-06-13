import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationPeriodAddComponent } from './examination-period-add.component';

describe('ExaminationPeriodAddComponent', () => {
  let component: ExaminationPeriodAddComponent;
  let fixture: ComponentFixture<ExaminationPeriodAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationPeriodAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationPeriodAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
