use project_thesis_final;




select * from exam;

update exam set start_date = '2025-03-24 20:15:00' where exam_id = 63;

select * from output_debug_result_java_core;

select e.title_exam, e.type_exam,m.mark_exam from mark_student m
right join exam e
on m.exam_id = e.exam_id
where m.student_id = '22662361';