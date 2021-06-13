import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ExaminationPeriod, Professor, Subject } from 'src/app/core/models';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { HttpExaminationPeriodService } from 'src/app/core/service/http-examination-period.service';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';

@Component({
  selector: 'app-exam-add',
  templateUrl: './exam-add.component.html',
  styleUrls: ['./exam-add.component.css']
})
export class ExamAddComponent implements OnInit {
examForm:FormGroup;
professors:Professor[];
  subjects:Subject[];
  periods: ExaminationPeriod[];
  selectedPeriod:ExaminationPeriod;
  constructor(private fb: FormBuilder,private _snackBar: MatSnackBar,private httpExam:HttpExamService,
      private httpExaminationPeriod:HttpExaminationPeriodService,private httpProfessor:HttpProfessorService) {
        this.httpExaminationPeriod.getAll().subscribe(response=> this.periods = response);
        this.httpProfessor.getAll().subscribe(response=> this.professors = response)
this.buildForm();
  }
buildForm() {
    this.examForm = this.fb.group(
      {
        id:[null],
        examinationPeriod: [null, [Validators.required]],
        professor: [null, [Validators.required]],
        subject:[null,[Validators.required]],
        exam_date:[null,[Validators.required]]


      }
    )
  }
  ngOnInit(): void {
  }

  onSubmit(){
    this.httpExam.save(this.examForm.value).pipe(

      catchError((error: HttpErrorResponse) => {
       // console.log(error.status);

        this.openSnackBar(error.error)
       if(error.status === 500){

        return throwError("Bad Request");
       }
        if (error.status === 400) {
            //console.log('You screwed');

            return throwError("Bad Request");
        }


    })


    ).subscribe(r=>{

      this.examForm.reset();
      this.openSnackBar("Exam  ADDED!");
     console.log(r);

    });
  }

  professorChanged(){


    this.subjects = this.examForm.controls['professor'].value.subjects
  }


   periodChanged(){
    this.selectedPeriod = this.examForm.controls['examinationPeriod'].value;
   }
  openSnackBar(message) {
    this._snackBar.open(message, "OK!");
  }



}
