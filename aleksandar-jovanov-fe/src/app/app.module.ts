import { NgModule } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core';

import { SubjectModule } from './features/subject/subject.module';

import { SharedModule } from './shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ProfessorModule } from './features/professor/professor.module';

import { ExaminationPeriodModule } from './features/examination-period/examination-period.module';
import { ExamModule } from './features/exam/exam.module';

import { StudentModule } from './features/student/student.module';
import { MatDialogModule } from '@angular/material/dialog';

import { ExamGradingModule } from './features/exam-grading/exam-grading.module';




@NgModule({
  declarations: [
    AppComponent,




  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    SharedModule,
    MatTabsModule,
    SubjectModule,
    ExaminationPeriodModule,
    ProfessorModule,
    StudentModule,
    ExamModule,
    NgbModule,
    ExamGradingModule,
    MatDialogModule




  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
