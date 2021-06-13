import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { City, Subject, Title } from 'src/app/core/models';
import { HttpCityService } from 'src/app/core/service/http-city.service';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { HttpTitleService } from 'src/app/core/service/http-title.service';
import { ProfessorEditDialogComponent } from '../professor-edit-dialog/professor-edit-dialog.component';

@Component({
  selector: 'app-professor-add',
  templateUrl: './professor-add.component.html',
  styleUrls: ['./professor-add.component.css']
})
export class ProfessorAddComponent implements OnInit {
  professorForm:FormGroup;
  cities: City[];
  subjects:Subject[];

  titles:Title[];

  constructor(private fb: FormBuilder,private httpTitle:HttpTitleService,
  private httpCity:HttpCityService,private httpSubject:HttpSubjectService,private httpProfessor:HttpProfessorService,private _snackBar: MatSnackBar) {
      this.httpCity.getAll().subscribe(response=>
        {

          this.cities = response;
        // console.log(this.cities.find(c=>c.cityCode == this.data.city.cityCode));



        })
        this.httpSubject.getAll().subscribe(response=>{

          this.subjects = response;


          })


          this.httpTitle.getAll().subscribe(response=>{
            this.titles = response;

          })
        this.buildForm();
    }

  ngOnInit(): void {
  }




  buildForm() {




    this.professorForm = this.fb.group(
      {
        id:[null],
        firstname: [null, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        lastname: [null, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        email: [null, [Validators.email]],
        adress: [null, [Validators.minLength(3),Validators.maxLength(50)]],
        city: [null, [Validators.maxLength(5)]],
        phone: [null, [Validators.maxLength(15)]],
        reelection_date: [null, [Validators.required]],
        title: [null, [Validators.required,Validators.maxLength(7)]],
        subjects:[[]]

      }
    )

  }

  onSubmit(){
    this.httpProfessor.save(this.professorForm.value).pipe(

      catchError((error: HttpErrorResponse) => {
       // console.log(error.status);

        if (error.status === 400) {
            //console.log('You screwed');
            return throwError("Bad Request");
        }


    })


    ).subscribe(r=>{

      this.professorForm.reset();
      this.openSnackBar();
     console.log(r);

    });
  }

  openSnackBar() {
    this._snackBar.open("PROFESSOR ADDED!", "OK!");
  }

}
