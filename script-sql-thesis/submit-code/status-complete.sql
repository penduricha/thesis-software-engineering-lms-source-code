select * from exam where exam_id = 47;

select * from mark_student where exam_id = 47;

delete from mark_student where mark_student_id = 1806;

select * from mark_student;

select * from question_java_core_exam where exam_id = 46;

select * from exam;

update exam set start_date = '2025-03-22 10:25:00'
where exam_id = 48;

-- Lay field detail_mark_student_id, lay 1806 lam chuan
select dms.detail_mark_student_id from mark_student m
left join detail_mark_student dms
on m.mark_student_id = dms.mark_student_id
where m.mark_student_id = 1806;

-- lay field result_question_java_core_id
select rqjc.result_question_java_core_id from mark_student m
left join  detail_mark_student dms
on m.mark_student_id = dms.mark_student_id
left join result_question_java_core rqjc
on dms.detail_mark_student_id = rqjc.detail_mark_student_id
where m.mark_student_id = 1806;

-- lay field output_debug_result_java_core_id
select odrjc.output_debug_java_core_id, odrjc.output_code_student, odrjc.output_debug_java_core_id
from mark_student m
left join  detail_mark_student dms
on m.mark_student_id = dms.mark_student_id
left join result_question_java_core rqjc
on dms.detail_mark_student_id = rqjc.detail_mark_student_id
left join output_debug_result_java_core odrjc
on rqjc.result_question_java_core_id = odrjc.result_question_java_core_id
where m.mark_student_id = 1835;


update exam set start_date = '2025-03-27 18:40:00'
where exam_id = 2;

select * from exam;

select * from detail_mark_student;


select * from question_java_core_exam;

select btcjc.output_expect from result_question_java_core rqjc
right join question_java_core_exam qjce
on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
right join bank_question_java_core bqjc
on qjce.question_java_core_id = bqjc.question_java_core_id
left join bank_test_case_java_core btcjc
on bqjc.question_java_core_id = btcjc.question_java_core_id;

select * from detail_mark_student;

select avg(d.detail_mark_exam) from detail_mark_student d
where d.mark_student_id = 3;

select * from mark_student;

# Trích xuất bảng