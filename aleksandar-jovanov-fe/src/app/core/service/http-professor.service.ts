import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


import { environment } from 'src/environments/environment';
import { Page } from '../models/page.dto';
import { Professor } from '../models';

@Injectable({
  providedIn: 'root'
})
export class HttpProfessorService {
  controlerPrefix = 'professor';
  constructor(private httpClient:HttpClient) { }


  getAll(){
    return this.httpClient.get<Professor[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Professor[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id:number) {
    return this.httpClient.get(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }
  delete(id:number) {
    return this.httpClient.delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  update(professor:Professor){
    return this.httpClient.put(`${environment.baseHttpURL}/${this.controlerPrefix}`,professor);
  }
  save(professor:Professor){
    console.log(professor);

    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`,professor);
  }
}
