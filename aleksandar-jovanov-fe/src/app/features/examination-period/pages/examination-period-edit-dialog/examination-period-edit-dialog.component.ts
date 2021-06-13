import { Component, OnInit,Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExaminationPeriod } from 'src/app/core/models';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { SubjectEditDialogComponent } from 'src/app/features/subject/pages/subject-edit-dialog/subject-edit-dialog.component';
@Component({
  selector: 'app-examination-period-edit-dialog',
  templateUrl: './examination-period-edit-dialog.component.html',
  styleUrls: ['./examination-period-edit-dialog.component.css']
})
export class ExaminationPeriodEditDialogComponent implements OnInit {

  examPeriodForm:FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public data: ExaminationPeriod,private fb: FormBuilder,private dialogRef: MatDialogRef<ExaminationPeriodEditDialogComponent>) {
    this.buildForm();

   }

  ngOnInit(): void {
  }

  buildForm() {
    // this.projectForm = new FormGroup({
    //   name: new FormControl('', [Validators.required]),
    //   description: new FormControl('', [Validators.maxLength(10)]),
    //   teamLead: new FormControl('', [Validators.required]),
    //   status: new FormControl('', [Validators.required]),
    // });
    this.examPeriodForm = this.fb.group(
      {
        id:[this.data.id, Validators.required],
        name: [this.data.name, [Validators.required]],
        starting_period: [this.data.starting_period, [Validators.required,Validators.maxLength(200)]],
        ending_period:[this.data.ending_period,[Validators.required,Validators.maxLength(200)]],
        active:[this.data.active,[Validators.required]]


      }
    )
  }


    onSubmit(){
      this.dialogRef.close({event:'Success', data:this.examPeriodForm.value,});
      //this.dialogRef.closeAll()

    }


}
