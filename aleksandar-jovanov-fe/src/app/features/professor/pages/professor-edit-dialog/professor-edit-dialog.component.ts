import { Component, OnInit,Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { City, Professor, Subject, Title } from 'src/app/core/models';
import { EmailValidator, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpCityService } from 'src/app/core/service/http-city.service';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { textChangeRangeIsUnchanged } from 'typescript';
import { sha1 } from '@angular/compiler/src/i18n/digest';
import { HttpTitleService } from 'src/app/core/service/http-title.service';


@Component({
  selector: 'app-professor-edit-dialog',
  templateUrl: './professor-edit-dialog.component.html',
  styleUrls: ['./professor-edit-dialog.component.css']
})
export class ProfessorEditDialogComponent implements OnInit {
  professorForm:FormGroup;

  cities: City[];
  subjects:Subject[];
  selectedSubjects:Subject[] = this.data.subjects;
  titles:Title[];
  constructor(@Inject(MAT_DIALOG_DATA) public data: Professor,private fb: FormBuilder,private httpTitle:HttpTitleService,
  private dialogRef: MatDialogRef<ProfessorEditDialogComponent>,private httpCity:HttpCityService,private httpSubject:HttpSubjectService) {

   this.httpCity.getAll().subscribe(response=>
    {

      this.cities = response;
    // console.log(this.cities.find(c=>c.cityCode == this.data.city.cityCode));
      this.professorForm.controls.city.setValue(this.cities.find(c=>c.cityCode == this.data.city.cityCode));

    })
    this.httpSubject.getAll().subscribe(response=>{
      let ids =this.data.subjects.map(s1=>{
        return s1.id;
      })
      this.subjects = response;
      this.selectedSubjects = this.subjects.filter(s=> {

          if(ids.includes(s.id)) return s;

      });


      })
      this.httpTitle.getAll().subscribe(response=>{
        this.titles = response;
        this.professorForm.controls.title.setValue(this.titles.find(t=>t.id == this.data.title.id));
      })



    this.buildForm();
   }

  ngOnInit(): void {

  }
  buildForm() {




    this.professorForm = this.fb.group(
      {
        id:[this.data.id, Validators.required],
        firstname: [this.data.firstname, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        lastname: [this.data.lastname, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        email: [this.data.email, [Validators.email]],
        adress: [this.data.adress, [Validators.minLength(3),Validators.maxLength(50)]],
        city: [null, [Validators.maxLength(5)]],
        phone: [this.data.phone, [Validators.maxLength(15)]],
        reelection_date: [this.data.reelection_date, [Validators.required]],
        title: [this.data.title, [Validators.required,Validators.maxLength(7)]],
        subjects:[this.selectedSubjects]

      }
    )
    this.professorForm.controls.subjects.setValue([this.data.subjects]);
    //this.professorForm.controls.city.setValue(this.data.city);
  }

  onSubmit(){
    this.dialogRef.close({event:'Success', data:this.professorForm.value,});
  }
}
