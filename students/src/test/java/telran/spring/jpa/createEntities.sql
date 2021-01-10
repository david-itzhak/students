delete from marks;
delete from students;
delete from subjects;

insert into students values(123, 'Moshe');
insert into subjects values(123, 'Java');
insert into marks (id, student_stid, subject_suid, mark)values( 1, 123, 123, 70);