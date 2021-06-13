import { Component, OnDestroy, OnInit,ViewChildren,QueryList } from '@angular/core';
import { SortableHeaderDirective ,SortEvent} from 'src/app/shared';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { ExaminationPeriod } from 'src/app/core/models';
import { Subject, throwError } from 'rxjs';
import { catchError, switchMap, takeUntil } from 'rxjs/operators';
import { HttpExaminationPeriodService } from 'src/app/core/service/http-examination-period.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { ExaminationPeriodInfoDialogComponent } from '../examination-period-info-dialog/examination-period-info-dialog.component';
import { ExaminationPeriodEditDialogComponent } from '../examination-period-edit-dialog/examination-period-edit-dialog.component';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
@Component({
  selector: 'app-examination-period-list',
  templateUrl: './examination-period-list.component.html',
  styleUrls: ['./examination-period-list.component.css'],
  providers: [MessageService]
})
export class ExaminationPeriodListComponent implements OnInit {
  examPeriods: ExaminationPeriod[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 10;
  examPeriodToDelete;
  destroy$: Subject<boolean> = new Subject();
  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  constructor(private httpExaminationPeriods:HttpExaminationPeriodService,private messageService:MessageService,
    private primengConfig:PrimeNGConfig, public dialog: MatDialog ,private _snackBar: MatSnackBar,private httpExamService:HttpExamService) { }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.loadExaminationPeriods();
  }
  ngOnDestroy(): void {
    this.destroy$.next(true);
  }
  loadExaminationPeriods() {
    this.httpExaminationPeriods.getByPage(this.currentPage-1, this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      response => {


        this.examPeriods = response.content;
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
    this.loadExaminationPeriods();
  }
  deleteExaminationPeriod(period:ExaminationPeriod){
    const confirmDialog = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Confirm Remove Subject',
        message: 'Are you sure, you want to remove Examination period: ' + period.name
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {


          this.httpExaminationPeriods.delete(period.id).pipe(

            catchError((error: HttpErrorResponse) => {


             if (error.status === 500) {

              this.openSnackBar("Unable to Delete Exam period, because it already contains some exams")
              return throwError("Bad Request");
          }
              if (error.status === 400) {
                  //console.log('You screwed');
                  return throwError("Bad Request");
              }
              if (error.status === 200) {
                //console.log('You screwed');
                 //this.subjects.splice(i, 1);

                 this.loadExaminationPeriods();
                return this.headers;
            }

          })


          ).subscribe(r=>{
            this.openSnackBar("Deleted Examination period")
          });
      }
    });



  }


onReject() {
  this.messageService.clear('c');
}

openInfoDialog(examPeriod:ExaminationPeriod){
 this.httpExamService.getByExaminationPeriod(examPeriod.id).subscribe(response=>{
   console.log(response);

  examPeriod.exams = response;
  this.dialog.open(ExaminationPeriodInfoDialogComponent, {
    data: examPeriod,
    width: "600px"
  });
  })

}

openEditDialog(examPeriod:ExaminationPeriod){
  this.httpExamService.getByExaminationPeriod(examPeriod.id).subscribe(response=>{
    console.log(response);

   examPeriod.exams = response;
  const dialogRef =  this.dialog.open(ExaminationPeriodEditDialogComponent, {
    data: examPeriod,
    width: "600px"
  });
  dialogRef.afterClosed().subscribe(result => {


    if(result){


      if(result.event == 'Success' && result.data){

        this.updateExaminationPeriod(result.data)
      }
    }

  });
  })
}
updateExaminationPeriod(examPeriod:ExaminationPeriod){
  console.log(examPeriod);

  this.httpExaminationPeriods.update(examPeriod).pipe(

    catchError((error: HttpErrorResponse) => {

      if(error.status === 500){
        this.openSnackBar("Exam Periods are overlaaping")
      }

      if (error.status === 400) {

          return throwError("Bad Request");
      }
      if (error.status === 200) {



        return this.headers;
    }

  })


  ).subscribe(r=>{

    this.openSnackBar("Exam period updated")
    this.loadExaminationPeriods();
  });

}

openSnackBar(message) {
  this._snackBar.open(message, "OK!");
}
}
