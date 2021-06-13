import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { City,  Student } from 'src/app/core/models';
import { HttpCityService } from 'src/app/core/service/http-city.service';

@Component({
  selector: 'app-student-edit-dialog',
  templateUrl: './student-edit-dialog.component.html',
  styleUrls: ['./student-edit-dialog.component.css']
})
export class StudentEditDialogComponent implements OnInit {

  studentForm:FormGroup
  cities: City[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: Student,private fb: FormBuilder,private dialogRef: MatDialogRef<StudentEditDialogComponent>,
  private httpCity:HttpCityService,) {
    this.httpCity.getAll().subscribe(response=>
      {

        this.cities = response;
      // console.log(this.cities.find(c=>c.cityCode == this.data.city.cityCode));
        this.studentForm.controls.city.setValue(this.cities.find(c=>c.cityCode == this.data.city.cityCode));

      })

      this.buildForm();
  }
  buildForm() {




    this.studentForm = this.fb.group(
      {
        id:[this.data.id],
        indexYear:[this.data.indexYear, [Validators.required,Validators.max(2100)]],
        indexNumber: [this.data.indexNumber, [Validators.required,Validators.minLength(3),Validators.maxLength(4)]],
        firstName: [this.data.firstName, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        lastName: [this.data.lastName, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        email: [this.data.email, [Validators.email]],
        adress: [this.data.adress, [Validators.minLength(3),Validators.maxLength(50)]],
        city: [this.data.city, [Validators.maxLength(5)]],
        currentYearOfStudy:[this.data.currentYearOfStudy,[Validators.required,Validators.max(7)]]


      }
    )
    //this.professorForm.controls.subjects.setValue([this.data.subjects]);
    //this.professorForm.controls.city.setValue(this.data.city);
  }

  onSubmit(){
    this.dialogRef.close({event:'Success', data:this.studentForm.value,});
  }
  ngOnInit(): void {
  }

}
