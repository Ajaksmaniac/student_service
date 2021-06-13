import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpExaminationPeriodService } from 'src/app/core/service/http-examination-period.service';

@Component({
  selector: 'app-examination-period-add',
  templateUrl: './examination-period-add.component.html',
  styleUrls: ['./examination-period-add.component.css']
})
export class ExaminationPeriodAddComponent implements OnInit {

  examPeriodForm:FormGroup;

  constructor(private fb: FormBuilder,private _snackBar: MatSnackBar,private httpExaminationPeriodService:HttpExaminationPeriodService) {
    this.buildForm();
   }
  buildForm() {
    this.examPeriodForm = this.fb.group(
      {
        id:[null],
        name: [null, [Validators.required]],
        starting_period: [null, [Validators.required,Validators.maxLength(200)]],
        ending_period:[null,[Validators.required,Validators.maxLength(200)]],
        active:[null,[Validators.required]]


      }
    )
  }

  ngOnInit(): void {
  }
  onSubmit(){
    this.httpExaminationPeriodService.save(this.examPeriodForm.value).pipe(

      catchError((error: HttpErrorResponse) => {
       // console.log(error.status);

       if(error.status === 500){
        this.openSnackBar("Exam Periods are overlaaping OR Starting date is after ending date")
        return throwError("Bad Request");
       }
        if (error.status === 400) {
            //console.log('You screwed');
            return throwError("Bad Request");
        }


    })


    ).subscribe(r=>{

      this.examPeriodForm.reset();
      this.openSnackBar("Examination period ADDED!");
     console.log(r);

    });
  }

  openSnackBar(message) {
    this._snackBar.open(message, "OK!");
  }


}
