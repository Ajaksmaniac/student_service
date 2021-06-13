import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExaminationPeriodComponent } from './examination-period.component';
import { ExaminationPeriodAddComponent } from './pages/examination-period-add/examination-period-add.component';
import { ExaminationPeriodListComponent } from './pages/examination-period-list/examination-period-list.component';
import { ExaminationPeriodInfoDialogComponent } from './pages/examination-period-info-dialog/examination-period-info-dialog.component';
import { ExaminationPeriodEditDialogComponent } from './pages/examination-period-edit-dialog/examination-period-edit-dialog.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ToastModule } from 'primeng/toast';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import {MatDividerModule} from '@angular/material/divider';
import { SharedModule } from 'src/app/shared';


@NgModule({
  declarations: [
    ExaminationPeriodComponent,
    ExaminationPeriodAddComponent,
    ExaminationPeriodListComponent,
    ExaminationPeriodInfoDialogComponent,
    ExaminationPeriodEditDialogComponent
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
    MatNativeDateModule,
    MatDividerModule,
    SharedModule
  ]
})
export class ExaminationPeriodModule { }
