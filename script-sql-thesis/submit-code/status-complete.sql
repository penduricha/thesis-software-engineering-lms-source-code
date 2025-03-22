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
select odrjc.output_debug_java_core_id from mark_student m
left join  detail_mark_student dms
on m.mark_student_id = dms.mark_student_id
left join result_question_java_core rqjc
on dms.detail_mark_student_id = rqjc.detail_mark_student_id
left join output_debug_result_java_core odrjc
on rqjc.result_question_java_core_id = odrjc.result_question_java_core_id
where m.mark_student_id = 1806;

select * from output_debug_result_java_core;
