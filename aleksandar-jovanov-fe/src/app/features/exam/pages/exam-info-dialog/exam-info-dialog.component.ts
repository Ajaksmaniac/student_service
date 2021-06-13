import { Component, OnInit,Inject } from '@angular/core';
import {  MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Exam } from 'src/app/core/models';
@Component({
  selector: 'app-exam-info-dialog',
  templateUrl: './exam-info-dialog.component.html',
  styleUrls: ['./exam-info-dialog.component.css']
})
export class ExamInfoDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Exam) {
    console.log(data.students);

  }

  ngOnInit(): void {
  }

}
