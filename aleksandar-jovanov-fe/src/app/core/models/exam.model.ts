import { ExaminationPeriod } from "./examination-period.model";
import { Professor } from "./professor.model";
import { Student } from "./student.model";
import { Subject } from "./subject.model";

export interface Exam{
  id?:number;
  examinationPeriod?:ExaminationPeriod;
  subject?:Subject;
  professor?:Professor;
  exam_date?:Date;
  students?:Student[];

}
