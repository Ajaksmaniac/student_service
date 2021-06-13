import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from '../models';
import { environment } from 'src/environments/environment';
import { Page } from '../models/page.dto';
@Injectable({
  providedIn: 'root'
})
export class HttpSubjectService {
  controlerPrefix = 'subject';
  constructor(private httpClient:HttpClient) { }


  getAll(){
    return this.httpClient.get<Subject[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Subject[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }
  delete(id:number) {
    return this.httpClient.delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  update(subject:Subject){
    return this.httpClient.put(`${environment.baseHttpURL}/${this.controlerPrefix}`,subject);
  }
  save(subject:Subject){
    console.log(subject);

    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`,subject);
  }
}
