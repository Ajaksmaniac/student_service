import { HttpErrorResponse } from '@angular/common/http';
import { QueryList } from '@angular/core';
import { Component, OnDestroy, OnInit, ViewChildren } from '@angular/core';
import { Subject, throwError } from 'rxjs';
import { catchError, switchMap, takeUntil } from 'rxjs/operators';
import {MessageService} from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';
import { Subject as SubjectModel} from 'src/app/core/models';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { SortableHeaderDirective ,SortEvent} from 'src/app/shared';
import { MatDialog } from '@angular/material/dialog';
import { SubjectInfoDialogComponent } from '../subject-info-dialog/subject-info-dialog.component';
import { SubjectEditDialogComponent } from '../subject-edit-dialog/subject-edit-dialog.component';

import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';



@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css'],
  providers: [MessageService]
})
export class SubjectListComponent implements OnInit,OnDestroy {
  subjects: SubjectModel[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 10;
  subjectToDelete;
  constructor(private httpSubject:HttpSubjectService,private messageService: MessageService, private primengConfig: PrimeNGConfig,
    public dialog: MatDialog,private _snackBar: MatSnackBar) { }
  ngOnDestroy(): void {
    this.destroy$.next(true);
  }
  destroy$: Subject<boolean> = new Subject();
  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.loadSubjecs();
  }

  loadSubjecs() {
    this.httpSubject.getByPage(this.currentPage-1, this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      response => {


        this.subjects = response.content;
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
    this.loadSubjecs();
  }
  deleteSubject(subject:SubjectModel){
    const confirmDialog = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Confirm Remove Subject',
        message: 'Are you sure, you want to remove an Subject: ' + subject.name
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this.httpSubject.delete(subject.id).pipe(

          catchError((error: HttpErrorResponse) => {
           // console.log(error.status);

           if (error.status === 500) {
            //console.log('You screwed');
            this.openSnackBar("Unable to delete this subject, because Professor already lectures this subject")
            return throwError("Bad Request");
        }
            if (error.status === 400) {
                //console.log('You screwed');
                this.openSnackBar("400 : Unable to delete this subject")
                return throwError("Bad Request");
            }
            if (error.status === 200) {
              //console.log('You screwed');
               //this.subjects.splice(i, 1);
               this.openSnackBar("Sucessfuly deleted subject")
               this.loadSubjecs();
              return this.headers;
          }

        })


        ).subscribe(r=>{
          //console.log(r);
        });

      }
    });
  }









openInfoDialog(subject:SubjectModel){
  this.dialog.open(SubjectInfoDialogComponent, {

    data: subject,

    width: "600px"

  });
}

openEditDialog(subject:SubjectModel){
  const dialogRef =  this.dialog.open(SubjectEditDialogComponent, {

    data: subject,

    width: "600px"

  });
  dialogRef.afterClosed().subscribe(result => {


    if(result){
      //console.log(result);

      if(result.event == 'Success' && result.data){
        //console.log(result.data);
        this.updateSubject(result.data)
      }
    }

  });
}

updateSubject(subject:SubjectModel){
  this.httpSubject.update(subject).pipe(

    catchError((error: HttpErrorResponse) => {
     // console.log(error.status);

      if (error.status === 400) {
          //console.log('You screwed');
          this.openSnackBar("Erro while updating subjects")
          return throwError("Bad Request");

      }
      if (error.status === 200) {
        //console.log('You screwed');
         //this.subjects.splice(i, 1);


        return this.headers;
    }

  })


  ).subscribe(r=>{
    this.openSnackBar("Subject updated")

    this.loadSubjecs();
  });

}
openSnackBar(message) {
  this._snackBar.open(message, "OK!");
}

}
