import { Component, OnInit,Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Exam, ExaminationPeriod, Professor, Subject } from 'src/app/core/models';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { HttpExaminationPeriodService } from 'src/app/core/service/http-examination-period.service';
import { i18nMetaToJSDoc } from '@angular/compiler/src/render3/view/i18n/meta';

@Component({
  selector: 'app-exam-edit-dialog',
  templateUrl: './exam-edit-dialog.component.html',
  styleUrls: ['./exam-edit-dialog.component.css']
})
export class ExamEditDialogComponent implements OnInit {

  examForm:FormGroup;
  professors:Professor[];
  subjects:Subject[];
  periods: ExaminationPeriod[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: Exam,private fb: FormBuilder,private httpProfessor:HttpProfessorService,
              private dialogRef: MatDialogRef<ExamEditDialogComponent>,private httpExaminationPeriodService:HttpExaminationPeriodService) {
                this.buildForm();
                this.httpExaminationPeriodService.getAll().subscribe(response=> this.periods = response)
                this.httpProfessor.getAll().subscribe(response=> this.professors = response)
                this.subjects = data.professor.subjects;
                //this.examForm.controls['professor'].setValue(this.data.professor);
               // console.log(this.examForm.controls['professor'].value );



   }


   buildForm() {




    this.examForm = this.fb.group(
      {
        id:[this.data.id, Validators.required],
        examinationPeriod: [this.data.examinationPeriod, [Validators.required]],
        subject: [this.data.subject, [Validators.required]],
        professor: [this.data.professor, [Validators.required]],
        exam_date: [this.data.exam_date, [Validators.required]],

      }

    )


  }

  onSubmit(){
    this.dialogRef.close({event:'Success', data:this.examForm.value,});
  }

  ngOnInit(): void {

  }

  professorChanged(){


   this.subjects = this.examForm.controls['professor'].value.subjects
  }

}
