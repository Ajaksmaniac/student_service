
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Exam, ExaminationPeriod, Student, StudentExam } from 'src/app/core/models';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { HttpExaminationPeriodService } from 'src/app/core/service/http-examination-period.service';
import { HttpStudentService } from 'src/app/core/service/http-student.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  examRegistrationForm:FormGroup;
  periods: ExaminationPeriod[];
  exams:Exam[];
  selectedExam:Exam;
  selectedPeriod:ExaminationPeriod;
  diff:number;
  //valid:boolean = false;
  constructor(private httpStudent:HttpStudentService,private fb: FormBuilder, private httpExaminationPeriod:HttpExaminationPeriodService,private httpExam:HttpExamService
    ,private _snackBar: MatSnackBar){

    this.httpExaminationPeriod.getAll().subscribe(response=>{
      this.periods = response
     this.selectedPeriod = this.periods[0];
     this.calculatDate();
     this.httpExam.getByExaminationPeriod(this.selectedPeriod.id).subscribe(response=>this.exams = response);
    });



    this.buildForm();
  }
  ngOnInit(): void {
  }
  onSubmit(){
    this.httpStudent.getByIndex(Number(this.examRegistrationForm.value.indexYear),Number(this.examRegistrationForm.value.indexNumber)).pipe(

      catchError((error: HttpErrorResponse) => {
       // console.log(error.status);
        this.openSnackBar("Student not found")
        if (error.status === 400) {
            //console.log('You screwed');
            return throwError("Bad Request");
        }


    })


    ).subscribe(
      response=>{
        this.addExam(response);

      }
    )
  }


  addExam(student:Student){
    var data:StudentExam = {
      id:null,
      student:{id:student.id},
      exam:{id:this.examRegistrationForm.value.exam.id},
      appliedAt:new Date()
    }
    console.log(data);

    this.httpExam.register(data).pipe(
      catchError((error: HttpErrorResponse) => {
        // console.log(error.status);
         this.openSnackBar(error.error)
         if (error.status === 400) {
             //console.log('You screwed');
             return throwError("Bad Request");
         }


     })
    ).subscribe(response=>{
      this.openSnackBar("Exam registered")
      this.examRegistrationForm.reset();
      console.log(response);

    })
  }
  periodChanged(){
    this.httpExam.getByExaminationPeriod(this.selectedPeriod.id).subscribe(response=>this.exams = response);
   this.diff =  Math.floor(( Date.parse(this.selectedPeriod.starting_period.toString()) - Date.parse(new Date().toString()) ) / 86400000);
  }

  buildForm(){
    this.examRegistrationForm = this.fb.group(
      {
        examinationPeriod:[this.selectedPeriod,[Validators.required]],
        exam:[this.selectedExam,[Validators.required]],
        indexYear:[null,[Validators.required,Validators.max(2100)]],
        indexNumber:[null,[Validators.required,,Validators.minLength(3),Validators.maxLength(4)]]

      }

    )
  }



  calculatDate(){
    this.diff =  Math.floor(( Date.parse(this.selectedPeriod.starting_period.toString()) - Date.parse(new Date().toString()) ) / 86400000);
  }

  openSnackBar(message) {
    this._snackBar.open(message, "OK!");
  }


}
