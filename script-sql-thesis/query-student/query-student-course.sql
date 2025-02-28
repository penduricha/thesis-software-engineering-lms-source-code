use project_thesis_final;

select * from student;

select * from course;

select * from exam;

select * from student_access_exam;

select s.student_id, s.date_of_birth, s.course_id
from student s where student_id = '23645291';

# cho sv hojc courseID = 2
update student set course_id = 2
where student_id = '23645291';

select * from bank_question_java_core
where content_question like '%số trung vị%';

select s.student_id, s.exam_id
from student_access_exam s
where s.exam_id = 23;

delete from student_access_exam;