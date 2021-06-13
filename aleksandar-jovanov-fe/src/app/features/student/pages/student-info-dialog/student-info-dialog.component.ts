import { Component, OnInit ,Inject} from '@angular/core';
import {  MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Student } from 'src/app/core/models';

@Component({
  selector: 'app-student-info-dialog',
  templateUrl: './student-info-dialog.component.html',
  styleUrls: ['./student-info-dialog.component.css']
})
export class StudentInfoDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Student) {
    console.log(data.exams);

  }

  ngOnInit(): void {
  }

}
