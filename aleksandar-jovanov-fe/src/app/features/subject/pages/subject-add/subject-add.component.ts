import { Component, OnInit } from '@angular/core';
import { Semester, Subject } from 'src/app/core/models';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { catchError } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-subject-add',
  templateUrl: './subject-add.component.html',
  styleUrls: ['./subject-add.component.css']
})
export class SubjectAddComponent implements OnInit {
  semester = Semester;
  subjectForm:FormGroup;

  constructor(private fb: FormBuilder,private httpSubject:HttpSubjectService,private _snackBar: MatSnackBar) {

    this.buildForm();
   }
   buildForm() {
    // this.projectForm = new FormGroup({
    //   name: new FormControl('', [Validators.required]),
    //   description: new FormControl('', [Validators.maxLength(10)]),
    //   teamLead: new FormControl('', [Validators.required]),
    //   status: new FormControl('', [Validators.required]),
    // });
    this.subjectForm = this.fb.group(
      {
        id:[null, ],
        name: ['', [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        description: ['', [Validators.maxLength(200)]],
        noOfEsp:[null,[Validators.required,Validators.max(8)]],
        yearOfStudy:[null,[Validators.required,Validators.maxLength(1)]],
        semester:[null,[Validators.required,Validators.max(48),Validators.min(2)]],


      }
    )
  }
  ngOnInit(): void {
  }
  getEnumKeys() {
    return Object.keys(Semester).map( key=> this.semester[key] );
  }

  onSubmit(){
    this.httpSubject.save(this.subjectForm.value).pipe(

      catchError((error: HttpErrorResponse) => {
       // console.log(error.status);

        if (error.status === 400) {
            //console.log('You screwed');
            return throwError("Bad Request");
        }


    })


    ).subscribe(r=>{

      this.subjectForm.reset();

      this.openSnackBar();

     console.log(r);

    });

  }

  openSnackBar() {
    this._snackBar.open("SUBJECT ADDED!", "OK!");
  }

}
