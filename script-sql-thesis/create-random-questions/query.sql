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

# create database project_thesis_final_2;
#
# drop database project_thesis_final_2;

select * from exam;

update exam set start_date = '2025-05-14 17:50:00'
where exam_id = 39;

select * from code_submit_java_class;

select * from detail_mark_student where detail_mark_student_id = 63;

select * from detail_mark_student where mark_student_id = 59;

select * from map_lecture_course;

select csjc.code_submit_java_class_id from code_submit_java_class csjc
right join detail_mark_student dms
on csjc.detail_mark_student_id = dms.detail_mark_student_id
right join mark_student ms
on dms.mark_student_id = ms.mark_student_id
where ms.mark_student_id = 60;

select * from lecture;

/*
 view các thông tin
 id, name, email, phoneNumber
 */

select l.lecture_id, email, name, phone_number from lecture l
where lecture_id = 1120050;

select * from exam;

update exam set start_date = '2025-05-16 15:00:00'