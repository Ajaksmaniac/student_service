import { HttpErrorResponse } from '@angular/common/http';
import { QueryList } from '@angular/core';
import { Component, OnDestroy, OnInit,ViewChildren } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { Subject, throwError } from 'rxjs';
import { catchError, switchMap, takeUntil } from 'rxjs/operators';
import { Student } from 'src/app/core/models';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { SortableHeaderDirective ,SortEvent} from 'src/app/shared';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { StudentEditDialogComponent } from '../student-edit-dialog/student-edit-dialog.component';
import { StudentEditExamDialogComponent } from '../student-edit-exam-dialog/student-edit-exam-dialog.component';
import { StudentInfoDialogComponent } from '../student-info-dialog/student-info-dialog.component';
@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
  providers: [MessageService]
})
export class StudentListComponent implements OnInit,OnDestroy {
  students: Student[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 10;
  studentToDelete;
  constructor(private httpStudent:HttpStudentService,private messageService: MessageService, private primengConfig: PrimeNGConfig,
    public dialog: MatDialog,private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.loadStudents();
  }
  ngOnDestroy(): void {
    this.destroy$.next(true);
  }
  destroy$: Subject<boolean> = new Subject();
  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  loadStudents() {
    this.httpStudent.getByPage(this.currentPage-1, this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      response => {


        this.students = response.content;
        this.totalItems = response.totalElements;
        this.pageSize = response.size;
        this.currentPage = response.number+1;
      }
    )
  }
  onSort(event: SortEvent) {
    console.log('sort event',event);

    this.headers.forEach( header => {
      if (header.sortable !== event.column) {
        header.direction = '';
      }
    })

     // TODO: call paging and sorting endpoint
  }
  onPageChange(page: number) {
    this.currentPage = page;
    this.loadStudents();
  }
  deleteStudent(student:Student){
    const confirmDialog = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Confirm Remove Subject',
        message: 'Are you sure, you want to remove Student: ' + student.firstName + ' ' +student.lastName + " and all of his exams registrations?"
      }
    })
      confirmDialog.afterClosed().subscribe(result => {
        if (result === true) {
          this.httpStudent.delete(student.id).pipe(

            catchError((error: HttpErrorResponse) => {
             // console.log(error.status);

             if (error.status === 500) {
              //console.log('You screwed');
              this.openSnackBar("Unable to delete this Student Becouse it already has registered exams")
              return throwError("Bad Request");
          }
              if (error.status === 400) {
                  //console.log('You screwed');
                  this.openSnackBar("400 : Unable to delete this Student")
                  return throwError("Bad Request");
              }
              if (error.status === 200) {
                //console.log('You screwed');
                 //this.subjects.splice(i, 1);
                 this.openSnackBar("Sucessfuly deleted Student and his registered exams")
                 this.loadStudents();
                return this.headers;
            }

          })


          ).subscribe(r=>{
            //console.log(r);
          });
        }
      });





  }

onReject() {
  this.messageService.clear('c');
}

openInfoDialog(student:Student){
  this.dialog.open(StudentInfoDialogComponent, {

    data: student,

    width: "600px"

  });
}
openEditDialog(student:Student){
  const dialogRef =  this.dialog.open(StudentEditDialogComponent, {

    data: student,

    width: "600px"

  });
  dialogRef.afterClosed().subscribe(result => {


    if(result){
      //console.log(result);

      if(result.event == 'Success' && result.data){
        //console.log(result.data);
        result.data.exams = student.exams;
        this.updateStudent(result.data)
      }
    }

  });
}
updateStudent(student:Student){
  this.httpStudent.update(student).pipe(

    catchError((error: HttpErrorResponse) => {
     // console.log(error.status);
     this.openSnackBar("Unable to update student: " + error.error)
      if (error.status === 400) {
          //console.log('You screwed');

          return throwError("Bad Request");

      }
      if (error.status === 200) {
        //console.log('You screwed');
         //this.subjects.splice(i, 1);


        return this.headers;
    }

  })


  ).subscribe(r=>{
    this.openSnackBar("Student updated")

    this.loadStudents();
  });

}

showUpdateExamDialog(student:Student){
  const dialogRef =  this.dialog.open(StudentEditExamDialogComponent, {

    data: student.exams,

    width: "600px"

  });
  dialogRef.afterClosed().subscribe(result => {


    if(result){
      //console.log(result);

      if(result.event == 'Success' && result.data){
        //console.log(result.data);
        student.exams = result.data.exams;
        this.updateStudentExams(student)
      }
    }

  });

}
updateStudentExams(student:Student){
  console.log(student);
  this.httpStudent.update(student).pipe(

    catchError((error: HttpErrorResponse) => {
     // console.log(error.status);
     this.openSnackBar("Unable to update student exams: " + error.error)
      if (error.status === 400) {
          //console.log('You screwed');

          return throwError("Bad Request");

      }
      if (error.status === 200) {
        //console.log('You screwed');
         //this.subjects.splice(i, 1);


        return this.headers;
    }

  })


  ).subscribe(r=>{
    this.openSnackBar("Student updated")

    this.loadStudents();
  });



}
openSnackBar(message) {
  this._snackBar.open(message, "OK!");
}

}
