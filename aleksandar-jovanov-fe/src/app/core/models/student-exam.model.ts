import { Exam } from "./exam.model";
import { Student } from "./student.model";

export interface StudentExam{
  id?:number;
  exam:Exam;
  appliedAt?:Date;
  student?:Student;
  grade?:number;

}
