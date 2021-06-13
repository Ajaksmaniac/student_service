import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FooterComponent } from './components/footer/footer.component';
import { LandingComponent } from './components/landing/landing.component';
import { RouterModule } from '@angular/router';
import { SortableHeaderDirective } from './directives/sortable-header.directive';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelect, MatSelectModule } from '@angular/material/select';
import { MatLabel } from '@angular/material/form-field';
import { MatDivider, MatDividerModule } from '@angular/material/divider';

import { MatDialogModule } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
@NgModule({
  declarations: [
    HeaderComponent,
    NotFoundComponent,
    FooterComponent,
    LandingComponent,
    SortableHeaderDirective,
    ConfirmationDialogComponent,

  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    RouterModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatDividerModule,
    MatDialogModule


  ],
  exports:[
    HeaderComponent,


  ]
})
export class SharedModule { }
