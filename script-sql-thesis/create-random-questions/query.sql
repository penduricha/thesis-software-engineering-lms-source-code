use project_thesis_final;

select * from bank_question_java_core;

select count(*) from bank_question_java_core;

select b.question_java_core_id, b.code_sample, b.content_question
from bank_question_java_core b order by rand() limit 2;

select * from question_java_core_exam
where exam_id = 45;

select exam_id, title_exam from exam;

select * from question_java_core_exam
where exam_id = 45;

select * from question_java_core_exam;

create database project_thesis_final_2;

drop database project_thesis_final_2;