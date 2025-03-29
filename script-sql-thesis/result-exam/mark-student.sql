use project_thesis_final;

select * from exam;

update exam set start_date = '2025-03-24 20:15:00' where exam_id = 63;

select * from output_debug_result_java_core;

select m.mark_student_id, e.title_exam, e.type_exam,m.mark_exam, e.retake_exam, e.topic_exam, m.exam_id
from mark_student m
right join exam e
on m.exam_id = e.exam_id
where m.student_id = '22662361';

select * from mark_student;

select dms.detail_mark_student_id,
       dms.date_submitted,
       dms.detail_mark_exam
from detail_mark_student dms
left join mark_student ms
on dms.mark_student_id = ms.mark_student_id
left join exam e
on ms.exam_id = e.exam_id
where ms.mark_student_id = 1864;

select * from detail_mark_student;

select detail_mark_exam from detail_mark_student
where mark_student_id = 1864 ;

select detail_mark_exam
from detail_mark_student
where mark_student_id = 1864
and date_submitted = (
    select max(date_submitted)
    from detail_mark_student
    where mark_student_id = 1864
)
group by detail_mark_exam;

select * from exam;

select * from result_question_java_core r
left join question_java_core_exam q
on r.question_java_core_exam_id = q.question_java_core_exam_id;

select * from result_question_java_core r
left join question_java_core_exam q
on r.question_java_core_exam_id = q.question_java_core_exam_id;

select * from mark_student;

select * from result_question_java_core;


select * from question_java_core_exam;

select * from exam;

drop table output_debug_result_java_core, result_question_java_core;