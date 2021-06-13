import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { City } from 'src/app/core/models';
import { HttpCityService } from 'src/app/core/service/http-city.service';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { StudentEditDialogComponent } from '../student-edit-dialog/student-edit-dialog.component';

@Component({
  selector: 'app-student-add',
  templateUrl: './student-add.component.html',
  styleUrls: ['./student-add.component.css']
})
export class StudentAddComponent implements OnInit {
  studentForm:FormGroup


  cities: City[];

  constructor(private fb: FormBuilder,
    private httpCity:HttpCityService,private httpStudent:HttpStudentService,private _snackBar: MatSnackBar) {
      this.httpCity.getAll().subscribe(response=>
        {

          this.cities = response;
        // console.log(this.cities.find(c=>c.cityCode == this.data.city.cityCode));
         // this.studentForm.controls.city.setValue(this.cities.find(c=>c.cityCode == this.data.city.cityCode));

        })

        this.buildForm();
     }

  ngOnInit(): void {
  }

  buildForm() {




    this.studentForm = this.fb.group(
      {
        id:[null],
        indexYear:[null, [Validators.required,Validators.max(2100)]],
        indexNumber: [null, [Validators.required,Validators.minLength(3),Validators.maxLength(4)]],
        firstName: [null, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        lastName: [null, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        email: [null, [Validators.email]],
        adress: [null, [Validators.minLength(3),Validators.maxLength(50)]],
        city: [null, [Validators.maxLength(5)]],
        currentYearOfStudy:[null,[Validators.required,Validators.max(7)]]


      }
    )
    //this.professorForm.controls.subjects.setValue([this.data.subjects]);
    //this.professorForm.controls.city.setValue(this.data.city);
  }

  onSubmit(){
    this.httpStudent.save(this.studentForm.value).pipe(

      catchError((error: HttpErrorResponse) => {
       // console.log(error.status);
        this.openSnackBar(error.error)
        if (error.status === 400) {
            //console.log('You screwed');
            return throwError("Bad Request");
        }


    })


    ).subscribe(r=>{

      this.studentForm.reset();
      this.openSnackBar("STUDENT ADDED!");
     console.log(r);

    });
  }



  openSnackBar(message) {
    this._snackBar.open(message, "OK!");
  }

}

