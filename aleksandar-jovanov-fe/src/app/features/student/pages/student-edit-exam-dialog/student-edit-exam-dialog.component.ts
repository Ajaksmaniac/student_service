import { Component, OnInit,Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { StudentExam } from 'src/app/core/models';

@Component({
  selector: 'app-student-edit-exam-dialog',
  templateUrl: './student-edit-exam-dialog.component.html',
  styleUrls: ['./student-edit-exam-dialog.component.css']
})
export class StudentEditExamDialogComponent implements OnInit {
  examsForm:FormGroup;
  exams: StudentExam[];
  selectedExams:StudentExam[];
  constructor(@Inject(MAT_DIALOG_DATA) public data:StudentExam[],private fb: FormBuilder,private dialogRef: MatDialogRef<StudentEditExamDialogComponent>) {
    this.exams = data;
    this.selectedExams = data;

    this.buildForm();
  }
  buildForm() {




    this.examsForm = this.fb.group(
      {
        exams:[this.selectedExams]

      }
    )

    //this.professorForm.controls.city.setValue(this.data.city);
  }

  onSubmit(){
    this.dialogRef.close({event:'Success', data:this.examsForm.value,});
  }
  ngOnInit(): void {
  }

}
