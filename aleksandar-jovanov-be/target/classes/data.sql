
/*SUBJECTS*/

INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Matematika 1","Uci se matematika",8,1,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Matematika 2","Uci se matematika 2",8,1,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Programiranje 1","Uci se Programiranje",8,1,"Summer");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Programiranje 2","Uci se Programiranje 2",8,1,"Summer");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Diskretna matematika","Diskrena matematika",8,1,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Baze podataka","Baze podataka",8,1,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Arhitektura racunara","Arhitektura racunara",6,1,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("OOP","Objektivno Orijentisano programiranje",7,1,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Metode razvoja sofvera","Objektivno Orijentisano programiranje",7,2,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Engleski 1","Engleski 1",7,1,"Summer");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Engleski 2","Engleski 1",7,2,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Web programiranje","Web programiranje",7,2,"Winter");
INSERT INTO subject (name,description,no_of_esp,year_of_study,semester) VALUES("Web Dizajn","Web programiranje",6,2,"Summer");

/*CITIES*/

INSERT INTO city VALUES(11000,"Beograd");
INSERT INTO city VALUES(13000,"Pancevo");
INSERT INTO city VALUES(37230,"Aleksandrovac");
INSERT INTO city VALUES(12000,"Pozarevac");

/*TITLES*/



INSERT INTO title(name) VALUES("Instructor");
INSERT INTO title(name) VALUES("Assistant Professor");
INSERT INTO title(name) VALUES("Associate Professor");
INSERT INTO title(name) VALUES("Professor");


/*PROFESSORS*/
INSERT INTO professor (firstname,lastname,email,adress,phone,reelection_date,city_code,title_id) VALUES("Prof 1","Prof 1","prof1@gmail.com","Adresa1","0611163700","2021-04-05",11000,1);
INSERT INTO professor_subjects VALUES(1,1);
INSERT INTO professor_subjects VALUES(1,2);
INSERT INTO professor (firstname,lastname,email,adress,phone,reelection_date,city_code,title_id) VALUES("Prof 2","Prof 2","prof2@gmail.com","Adresa2","0611163700","2021-04-05",12000,1);
INSERT INTO professor_subjects VALUES(2,3);
INSERT INTO professor_subjects VALUES(2,4);

INSERT INTO professor (firstname,lastname,email,adress,phone,reelection_date,city_code,title_id) VALUES("Prof 3","Prof 3","prof3@gmail.com","Adresa3","0611163700","2021-04-05",12000,2);
INSERT INTO professor_subjects VALUES(3,3);
INSERT INTO professor_subjects VALUES(3,4);
INSERT INTO professor_subjects VALUES(3,5);
INSERT INTO professor_subjects VALUES(3,6);
INSERT INTO professor_subjects VALUES(3,7);



/*STUDENTS*/

INSERT INTO STUDENT(index_year,index_number,adress,current_year,email,firstname,lastname,city_code) VALUES('2017','2000','Neka adresa 1',4,'ajaka@gmail.com','Aleksandar','Jovanov',12000);
INSERT INTO STUDENT(index_year,index_number,adress,current_year,email,firstname,lastname,city_code) VALUES('2018','2001','Neka adresa 2 ',4,'em2@gmail.com','s2','s2',11000);
INSERT INTO STUDENT(index_year,index_number,adress,current_year,email,firstname,lastname,city_code) VALUES('2018','2002','Neka adresa 3',4,'em3@gmail.com','s3','s3',11000);
INSERT INTO STUDENT(index_year,index_number,adress,current_year,email,firstname,lastname,city_code) VALUES('2019','2003','Neka adresa 4',4,'em4@gmail.com','s4','d4',11000);


/*EXAMINATION PERIODS*/
INSERT INTO examination_period(active,ending_period,name,starting_period) VALUES(0,'2021-04-25','p1','2021-04-15');
INSERT INTO examination_period(active,ending_period,name,starting_period) VALUES(0,'2021-04-29','p2','2021-04-26');
INSERT INTO examination_period(active,ending_period,name,starting_period) VALUES(0,'2021-05-14','p3','2021-05-05');
INSERT INTO examination_period(active,ending_period,name,starting_period) VALUES(0,'2021-05-22','p4','2021-05-15');
INSERT INTO examination_period(active,ending_period,name,starting_period) VALUES(1,'2021-07-15','p5','2021-06-20');
/*EXAMS*/

INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-16',1,1,1);
INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-17',1,1,2);
INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-17',3,3,3);
INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-17',3,3,4);
INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-17',3,3,5);
INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-17',3,3,3);
INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-17',4,3,4);
INSERT INTO exam(exam_date,examination_period_id,professor_id,subject_id) VALUES('2021-04-17',4,3,5);


INSERT INTO examination_period_exams(examination_period_entity_id,exams_id) VALUES (1,1);
INSERT INTO examination_period_exams(examination_period_entity_id,exams_id) VALUES (1,2);
INSERT INTO examination_period_exams(examination_period_entity_id,exams_id) VALUES (4,3);
INSERT INTO examination_period_exams(examination_period_entity_id,exams_id) VALUES (4,4);
INSERT INTO examination_period_exams(examination_period_entity_id,exams_id) VALUES (4,5);


/*Registered exams*/
INSERT INTO student_exam(applied_at,exam_id,student_id,grade) VALUES('2021-04-16',1,1,8);
INSERT INTO student_exam(applied_at,exam_id,student_id,grade) VALUES('2021-04-16',2,2,7);
INSERT INTO student_exam(applied_at,exam_id,student_id,grade) VALUES('2021-04-16',1,2,6);
INSERT INTO student_exam(applied_at,exam_id,student_id,grade) VALUES('2021-04-16',2,1,10);
INSERT INTO student_exam(applied_at,exam_id,student_id,grade) VALUES('2021-04-16',1,3,7);
INSERT INTO student_exam(applied_at,exam_id,student_id,grade) VALUES('2021-04-16',2,3,0);
