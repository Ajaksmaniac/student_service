import { Exam } from "./exam.model";

export interface ExaminationPeriod{
  id:number;
  name:string;
  starting_period:Date;
  ending_period:Date;
  active:boolean;
  exams:Exam[]
}
