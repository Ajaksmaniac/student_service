import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Page } from '../models/page.dto';
import { Exam, StudentExam } from '../models';
@Injectable({
  providedIn: 'root'
})
export class HttpExamService {
  controlerPrefix = 'exam';
  constructor(private httpClient:HttpClient) { }


  getAll(){
    return this.httpClient.get<Exam[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Exam[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }
  getAllREgisteredByPage(page:number, size: number) {
    console.log(`${environment.baseHttpURL}/${this.controlerPrefix}/registered/page?page=${page}&size=${size}`);

    return this.httpClient.get<Page<StudentExam[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/registered/page?page=${page}&size=${size}`)
  }
  getByExaminationPeriod(id:number) {
    return this.httpClient.get<Exam[]>(`${environment.baseHttpURL}/${this.controlerPrefix}/period/${id}`)
  }

  updateStudentExam(studentExam:StudentExam){
    return this.httpClient.put<StudentExam>(`${environment.baseHttpURL}/${this.controlerPrefix}/register`,studentExam);
  }

  delete(id:number) {
    return this.httpClient.delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  update(exam:Exam){
    return this.httpClient.put(`${environment.baseHttpURL}/${this.controlerPrefix}`,exam);
  }
  save(exam:Exam){
    console.log(exam);

    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`,exam);
  }

  register(data:StudentExam){
    console.log(JSON.stringify(data));
    console.log(`${environment.baseHttpURL}/${this.controlerPrefix}/register`);

    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}/register`,data);
  }
}
