import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubjectComponent } from './subject.component';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';
import { SubjectAddComponent } from './pages/subject-add/subject-add.component';
import {  MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {ToastModule} from 'primeng/toast';
import {RippleModule} from 'primeng/ripple';
import {ButtonModule} from 'primeng/button';
import { SubjectInfoDialogComponent } from './pages/subject-info-dialog/subject-info-dialog.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { SubjectEditDialogComponent } from './pages/subject-edit-dialog/subject-edit-dialog.component';

import {MatSnackBarModule} from '@angular/material/snack-bar';

import { SharedModule } from 'src/app/shared';



@NgModule({
  declarations: [
    SubjectComponent,
    SubjectListComponent,
    SubjectAddComponent,
    SubjectInfoDialogComponent,
    SubjectEditDialogComponent,



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
    MatSnackBarModule,
    MatDialogModule,
    SharedModule,





  ],
  exports:[


    SubjectComponent,
    SubjectListComponent,
    SubjectAddComponent








  ]

})
export class SubjectModule { }
