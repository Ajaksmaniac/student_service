import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { City } from '../models';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpCityService {

  controlerPrefix = 'city';
  constructor(private httpClient:HttpClient) { }


  getAll(){
    return this.httpClient.get<City[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<City[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }
  delete(id:number) {
    return this.httpClient.delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  update(city:City){
    return this.httpClient.put(`${environment.baseHttpURL}/${this.controlerPrefix}`,city);
  }
  save(city:City){
    console.log(city);

    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`,city);
  }
}
