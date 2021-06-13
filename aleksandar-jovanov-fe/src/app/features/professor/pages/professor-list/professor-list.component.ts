import { Component, OnDestroy, OnInit,ViewChildren,QueryList } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { Professor } from 'src/app/core/models';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { Subject, throwError } from 'rxjs';
import { catchError, switchMap, takeUntil } from 'rxjs/operators';
import { SortableHeaderDirective ,SortEvent} from 'src/app/shared';
import { HttpErrorResponse } from '@angular/common/http';
import { ProfessorInfoDialogComponent } from '../professor-info-dialog/professor-info-dialog.component';
import { ProfessorEditDialogComponent } from '../professor-edit-dialog/professor-edit-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
  providers: [MessageService]
})
export class ProfessorListComponent implements OnInit,OnDestroy {
  professors: Professor[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 10;
  professorToDelete;
  destroy$: Subject<boolean> = new Subject();

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;


  constructor(private httpProfessor:HttpProfessorService,private messageService:MessageService,
    private primengConfig:PrimeNGConfig, public dialog: MatDialog ,private _snackBar: MatSnackBar) { }
  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.loadProfessors();
  }
  loadProfessors() {
    this.httpProfessor.getByPage(this.currentPage-1, this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      response => {


        this.professors = response.content;
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
    this.loadProfessors();
  }
  deleteProfessor(professor:Professor){
    const confirmDialog = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Confirm Remove Subject',
        message: 'Are you sure, you want to remove  Professor: ' + professor.firstname + ' ' +professor.lastname
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this.httpProfessor.delete(professor.id).pipe(

          catchError((error: HttpErrorResponse) => {


           if (error.status === 500) {

            this.openSnackBar("Unable to Delete professor")
            return throwError("Bad Request");
        }
            if (error.status === 400) {
                //console.log('You screwed');
                return throwError("Bad Request");
            }
            if (error.status === 200) {
              //console.log('You screwed');
               //this.subjects.splice(i, 1);

               this.loadProfessors();
              return this.headers;
          }

        })


        ).subscribe(r=>{
          this.openSnackBar("Professor Deleted")
        });
      }
      });



  }
  showConfirm(id) {
    this.professorToDelete = id;
    this.messageService.clear();
    this.messageService.add({key: 'c', sticky: true, severity:'warn', summary:'Are you sure?', detail:'Confirm to proceed'});
  }
onConfirm() {
  this.deleteProfessor(this.professorToDelete);
  this.professorToDelete = null;
  this.messageService.clear('c');

}

onReject() {
  this.messageService.clear('c');
}

openInfoDialog(professor:Professor){
  this.dialog.open(ProfessorInfoDialogComponent, {
    data: professor,
    width: "600px"
  });
}
openEditDialog(professor:Professor){
  const dialogRef =  this.dialog.open(ProfessorEditDialogComponent, {
    data: professor,
    width: "600px"
  });
  dialogRef.afterClosed().subscribe(result => {


    if(result){


      if(result.event == 'Success' && result.data){

        this.updateProfessor(result.data)
      }
    }

  });
}
updateProfessor(professor:Professor){
  console.log(professor);

  this.httpProfessor.update(professor).pipe(

    catchError((error: HttpErrorResponse) => {

     this.openSnackBar("Unable to Update professor")
      if (error.status === 400) {

          return throwError("Bad Request");
      }
      if (error.status === 200) {



        return this.headers;
    }

  })


  ).subscribe(r=>{

    this.openSnackBar("Professor updated")
    this.loadProfessors();
  });

}

openSnackBar(message) {
  this._snackBar.open(message, "OK!");
}
}
