use project_thesis_final;

select * from question_java_core_exam where exam_id = 35;

select * from exam;

select question_java_core_id from bank_question_java_core;

delete from exam where exam_id = 35;

select title_exam from exam where course_id = 2 and title_exam = 'Java';

select input_test, output_expect, note from bank_test_case_java_core
where question_java_core_id = 8;

select distinct question_java_core_id
from question_java_core_exam;