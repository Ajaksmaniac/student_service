import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentComponent } from './student.component';
import { StudentAddComponent } from './pages/student-add/student-add.component';
import { StudentListComponent } from './pages/student-list/student-list.component';
import { StudentInfoDialogComponent } from './pages/student-info-dialog/student-info-dialog.component';
import { StudentEditDialogComponent } from './pages/student-edit-dialog/student-edit-dialog.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { MatLabel } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { StudentEditExamDialogComponent } from './pages/student-edit-exam-dialog/student-edit-exam-dialog.component';



@NgModule({
  declarations: [
    StudentComponent,
    StudentAddComponent,
    StudentListComponent,
    StudentInfoDialogComponent,
    StudentEditDialogComponent,
    StudentEditExamDialogComponent
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
    RippleModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,

  ],
  exports:[
    StudentComponent,
    StudentAddComponent,
    StudentListComponent,
    StudentInfoDialogComponent,
    StudentEditDialogComponent,
    StudentEditExamDialogComponent
  ]
})
export class StudentModule { }
