import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ExaminationPeriod, Professor } from '../models';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpExaminationPeriodService {

  controlerPrefix = 'period';
  constructor(private httpClient:HttpClient) { }


  getAll(){
    return this.httpClient.get<ExaminationPeriod[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<ExaminationPeriod[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }
  delete(id:number) {
    return this.httpClient.delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  update(period:ExaminationPeriod){
    return this.httpClient.put(`${environment.baseHttpURL}/${this.controlerPrefix}`,period);
  }
  save(period:ExaminationPeriod){
    console.log(period);

    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`,period);
  }

  getActive(){
    return this.httpClient.get<ExaminationPeriod>(`${environment.baseHttpURL}/${this.controlerPrefix}/active`)
  }
}
