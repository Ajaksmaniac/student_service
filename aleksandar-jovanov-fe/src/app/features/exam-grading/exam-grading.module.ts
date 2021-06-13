import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExamGradingComponent } from './exam-grading.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { StudentExamHistoryListComponent } from './pages/student-exam-history-list/student-exam-history-list.component';
import { StudentUngradedExamsListComponent } from './pages/student-ungraded-exams-list/student-ungraded-exams-list.component';
import { StudentExamEditDialogComponent } from './pages/student-exam-edit-dialog/student-exam-edit-dialog.component';



@NgModule({
  declarations: [
    ExamGradingComponent,
    StudentExamHistoryListComponent,
    StudentUngradedExamsListComponent,
    StudentExamEditDialogComponent
  ],
  imports: [
    CommonModule,
    MatTabsModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    MatIconModule,
    MatButtonModule,
    ToastModule,
    ButtonModule,

    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,

  ]
})
export class ExamGradingModule { }
