import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamGradingComponent } from './features/exam-grading/exam-grading.component';
import { ExamComponent } from './features/exam/exam.component';
import { ExaminationPeriodComponent } from './features/examination-period/examination-period.component';

import { ProfessorComponent } from './features/professor/professor.component';
import { StudentComponent } from './features/student/student.component';

import { SubjectComponent } from './features/subject/subject.component';
import { NotFoundComponent } from './shared';
import { LandingComponent } from './shared/components/landing/landing.component';

const routes: Routes = [
  {path:'',component:LandingComponent},
  {path:'subject',component:SubjectComponent},
  {path:'exam',component:ExamComponent},
  {path:'professor',component:ProfessorComponent},
  {path:'grading',component:ExamGradingComponent},
  {path: 'period', component: ExaminationPeriodComponent},
  {path: 'student', component: StudentComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
