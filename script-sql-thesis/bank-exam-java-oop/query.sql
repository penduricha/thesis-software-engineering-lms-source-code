use project_thesis_final;

show columns from bank_test_java_oop;

select * from bank_test_java_oop;

select * from exam;

select exam.title_exam from exam;

update exam set start_date = '2025-04-08 13:00:00'
where exam_id = 14;

select * from bank_test_java_oop;

show columns from bank_test_java_oop;

select * from exam_java_oop;

select * from course;

select * from exam;

update exam set start_date = '2025-04-19 12:00:00' where exam_id =7;

select btjo.bank_test_java_oop_id from exam e
left join exam_java_oop ejo
on e.exam_id = ejo.exam_id
right join bank_test_java_oop btjo
on ejo.bank_test_java_oop_id = btjo.bank_test_java_oop_id
where e.exam_id = 6;

select * from exam_java_oop;



select * from student_access_exam;

select * from result_question_java_core;