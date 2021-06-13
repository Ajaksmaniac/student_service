import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationPeriodComponent } from './examination-period.component';

describe('ExaminationPeriodComponent', () => {
  let component: ExaminationPeriodComponent;
  let fixture: ComponentFixture<ExaminationPeriodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationPeriodComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationPeriodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
