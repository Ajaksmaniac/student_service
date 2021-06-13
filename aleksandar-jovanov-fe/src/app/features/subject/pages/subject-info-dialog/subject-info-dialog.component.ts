import { Component, OnInit ,Inject} from '@angular/core';
import {  MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Subject } from 'src/app/core/models';
@Component({
  selector: 'app-subject-info-dialog',
  templateUrl: './subject-info-dialog.component.html',
  styleUrls: ['./subject-info-dialog.component.css']
})
export class SubjectInfoDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Subject) { }

  ngOnInit(): void {
  }



}
