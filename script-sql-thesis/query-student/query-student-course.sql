use project_thesis_final;

select * from student;

select version();

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
from student_access_exam s;

select s.student_id, s.exam_id
from student_access_exam s
where s.exam_id = 24;

# delete
delete from student_access_exam;

select * from bank_question_java_core
left join
bank_test_case_java_core btcjc
on bank_question_java_core.question_java_core_id = btcjc.question_java_core_id
where btcjc.question_java_core_id = 2;

select * from question_java_core_exam where exam_id = 24;