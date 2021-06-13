
import { City } from "./city.model";
import { Exam } from "./exam.model";
import { StudentExam } from "./student-exam.model";

export interface Student{
  id?:number;
  indexNumber?:string
  indexYear?:number;
  firstName?:String;
  lastName?:String;
  email?:String;
  city?:City;
  adress?:String;
  currentYearOfStudy?:number;
  exams?:StudentExam[];

}
