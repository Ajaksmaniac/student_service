import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ExaminationPeriod } from 'src/app/core/models';

@Component({
  selector: 'app-examination-period-info-dialog',
  templateUrl: './examination-period-info-dialog.component.html',
  styleUrls: ['./examination-period-info-dialog.component.css']
})
export class ExaminationPeriodInfoDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: ExaminationPeriod) { }

  ngOnInit(): void {
  }

}


