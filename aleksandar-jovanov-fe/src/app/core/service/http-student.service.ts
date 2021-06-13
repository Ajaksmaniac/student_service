import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Professor, Student } from '../models';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpStudentService {

  controlerPrefix = 'student';
  constructor(private httpClient:HttpClient) { }


  getAll(){
    return this.httpClient.get<Student[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Student[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id:number) {
    return this.httpClient.get(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  getByIndex(year:number,number:number,) {
    console.log(`${environment.baseHttpURL}/${this.controlerPrefix}/index/${year}/${number}`);

    return this.httpClient.get<Student>(`${environment.baseHttpURL}/${this.controlerPrefix}/index/${year}/${number}`)
  }
  delete(id:number) {
    return this.httpClient.delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  update(student:Student){
    return this.httpClient.put(`${environment.baseHttpURL}/${this.controlerPrefix}`,student);
  }
  save(student:Student){
    console.log(student);

    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`,student);
  }
}
