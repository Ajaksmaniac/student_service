import { Component, OnInit,Inject } from '@angular/core';
import { Professor } from 'src/app/core/models';
import {  MAT_DIALOG_DATA} from '@angular/material/dialog';
@Component({
  selector: 'app-professor-info-dialog',
  templateUrl: './professor-info-dialog.component.html',
  styleUrls: ['./professor-info-dialog.component.css']
})
export class ProfessorInfoDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Professor) { }

  ngOnInit(): void {
  }

}
