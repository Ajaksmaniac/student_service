import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { StudentExam } from 'src/app/core/models';
import { StudentEditDialogComponent } from 'src/app/features/student/pages/student-edit-dialog/student-edit-dialog.component';

@Component({
  selector: 'app-student-exam-edit-dialog',
  templateUrl: './student-exam-edit-dialog.component.html',
  styleUrls: ['./student-exam-edit-dialog.component.css']
})
export class StudentExamEditDialogComponent implements OnInit {
  studentExamForm:FormGroup;
  constructor(@Inject(MAT_DIALOG_DATA) public data: StudentExam,private dialogRef: MatDialogRef<StudentExamEditDialogComponent>
  ,private fb: FormBuilder) {
    this.buildForm();
    console.log(this.data);

  }

  ngOnInit(): void {
  }


  buildForm(){
    this.studentExamForm = this.fb.group(
      {
        id:[this.data.id],
        exam:[this.data.exam, [Validators.required]],
        appliedAt: [this.data.appliedAt, [Validators.required]],
        student: [this.data.student, [Validators.required]],
        grade: [this.data.grade, [Validators.required,Validators.maxLength(1),Validators.max(10),Validators.min(1)]],



      }
    )
  }
  onSubmit(){
    this.dialogRef.close({event:'Success', data:this.studentExamForm.value,});
  }


}
