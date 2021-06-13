import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfessorComponent } from './professor.component';
import { ProfessorAddComponent } from './pages/professor-add/professor-add.component';
import { ProfessorEditDialogComponent } from './pages/professor-edit-dialog/professor-edit-dialog.component';
import { ProfessorInfoDialogComponent } from './pages/professor-info-dialog/professor-info-dialog.component';
import { ProfessorListComponent } from './pages/professor-list/professor-list.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SharedModule } from 'src/app/shared';

@NgModule({
  declarations: [
    ProfessorComponent,

    ProfessorEditDialogComponent,
    ProfessorInfoDialogComponent,
    ProfessorListComponent,
    ProfessorAddComponent
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
    MatDatepickerModule,
    MatNativeDateModule, SharedModule
  ]
})
export class ProfessorModule { }
