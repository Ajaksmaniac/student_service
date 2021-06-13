import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Subject } from 'rxjs/internal/Subject';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { catchError, switchMap, takeUntil } from 'rxjs/operators';
import { SortableHeaderDirective ,SortEvent} from 'src/app/shared';
import { Exam } from 'src/app/core/models';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { ExamInfoDialogComponent } from '../exam-info-dialog/exam-info-dialog.component';
import { ExamEditDialogComponent } from '../exam-edit-dialog/exam-edit-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css'],
  providers: [MessageService]
})
export class ExamListComponent implements OnInit,OnDestroy {

  exams: Exam[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 10;
 examToDelete;
 destroy$: Subject<boolean> = new Subject();

 @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  constructor(private httpExam:HttpExamService,private primengConfig:PrimeNGConfig, public dialog: MatDialog ,private _snackBar: MatSnackBar,
    private messageService:MessageService) { }
  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.loadExams();
  }
  loadExams(){
    this.httpExam.getByPage(this.currentPage-1, this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      response => {


        this.exams = response.content;
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
    this.loadExams();
  }
  deleteExam(exam:Exam){
    const confirmDialog = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Confirm Remove Subject',
        message: 'Are you sure, you want to remove Exam: ' + exam.examinationPeriod.name + ': ' +exam.subject.name+" and all of his student registrations?"
      }
    })
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this.httpExam.delete(exam.id).pipe(

          catchError((error: HttpErrorResponse) => {

            this.openSnackBar("Unable to delete exam")
           if (error.status === 500) {


            return throwError("Bad Request");
        }
            if (error.status === 400) {
                //console.log('You screwed');
                return throwError("Bad Request");
            }
            if (error.status === 200) {
              //console.log('You screwed');
               //this.subjects.splice(i, 1);

               this.loadExams();
              return this.headers;
          }

        })


        ).subscribe(r=>{
          this.openSnackBar("Deleted exam and all his student registrations")
        });
      }
    });




  }



  onReject() {
    this.messageService.clear('c');
  }

  openInfoDialog(exam:Exam){
    this.dialog.open(ExamInfoDialogComponent, {
      data: exam,
      width: "600px"
    });
  }

  openEditDialog(exam:Exam){
    const dialogRef =  this.dialog.open(ExamEditDialogComponent, {
      data: exam,
      width: "600px"
    });
    dialogRef.afterClosed().subscribe(result => {


      if(result){


        if(result.event == 'Success' && result.data){

          this.updateExam(result.data)
        }
      }

    });
  }

  updateExam(exam:Exam){
    console.log(exam);

    this.httpExam.update(exam).pipe(

      catchError((error: HttpErrorResponse) => {

       this.openSnackBar(error.error)
        if (error.status === 400) {

            return throwError("Bad Request");
        }
        if (error.status === 200) {



          return this.headers;
      }

    })


    ).subscribe(r=>{

      this.openSnackBar("Exam updated")
      this.loadExams();
    });

  }


  openSnackBar(message) {
    this._snackBar.open(message, "OK!");
  }



}
