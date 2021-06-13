import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject, throwError } from 'rxjs';
import { takeUntil, catchError } from 'rxjs/operators';
import { StudentExam, Subject as SubjectModel } from 'src/app/core/models';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared';
import { StudentExamEditDialogComponent } from '../student-exam-edit-dialog/student-exam-edit-dialog.component';

@Component({
  selector: 'app-student-ungraded-exams-list',
  templateUrl: './student-ungraded-exams-list.component.html',
  styleUrls: ['./student-ungraded-exams-list.component.css']
})
export class StudentUngradedExamsListComponent implements OnInit {

  student_exams: StudentExam[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 10;
  constructor(private httpExam:HttpExamService, public dialog: MatDialog,private _snackBar: MatSnackBar) { }
  destroy$: Subject<boolean> = new Subject();
  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadStudentExams();
  }
  ngOnDestroy(): void {
    this.destroy$.next(true);
  }
  loadStudentExams() {
    this.httpExam.getAllREgisteredByPage(this.currentPage-1, this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      response => {
        console.log(response);


        this.student_exams = response.content.filter(e=>e.grade == 0);
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
    this.loadStudentExams();
  }
  openEditExamDialog(studentExam:StudentExam){
    const dialogRef =  this.dialog.open(StudentExamEditDialogComponent, {

      data: studentExam,

      width: "600px"

    });
    dialogRef.afterClosed().subscribe(result => {


      if(result){
        //console.log(result);

        if(result.event == 'Success' && result.data){
          //console.log(result.data);

          this.updateStudent(result.data);
        }
      }

    });


  }

  updateStudent(studentExam:StudentExam){
    this.httpExam.updateStudentExam(studentExam).pipe(

      catchError((error: HttpErrorResponse) => {
       // console.log(error.status);
       this.openSnackBar("Unable to update Student Exam: " + error.error)
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
      this.openSnackBar("Student Exam updated")

      this.loadStudentExams();
    });

  }

  openSnackBar(message) {
    this._snackBar.open(message, "OK!");
  }


}
