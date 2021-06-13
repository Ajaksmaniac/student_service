import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Semester, Subject } from 'src/app/core/models';
@Component({
  selector: 'app-subject-edit-dialog',
  templateUrl: './subject-edit-dialog.component.html',
  styleUrls: ['./subject-edit-dialog.component.css']
})
export class SubjectEditDialogComponent implements OnInit {

  semester = Semester;
  subjectForm:FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public data: Subject,private fb: FormBuilder,private dialogRef: MatDialogRef<SubjectEditDialogComponent>) {
    this.buildForm();
  }

  ngOnInit(): void {
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
        id:[this.data.id, Validators.required],
        name: [this.data.name, [Validators.required,Validators.minLength(3),Validators.maxLength(30)]],
        description: [this.data.description, [Validators.maxLength(200)]],
        noOfEsp:[this.data.noOfEsp,[Validators.required,Validators.max(8),Validators.maxLength(1)]],
        yearOfStudy:[this.data.yearOfStudy,[Validators.required,Validators.maxLength(1)]],
        semester:[this.data.semester,[Validators.required,Validators.max(48),Validators.min(2)]],


      }
    )
  }
  getEnumKeys() {
    return Object.keys(Semester).map( key=> this.semester[key] );
  }


  onSubmit(){
    this.dialogRef.close({event:'Success', data:this.subjectForm.value,});
    //this.dialogRef.closeAll()

  }
}
