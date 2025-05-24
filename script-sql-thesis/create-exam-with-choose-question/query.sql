use project_thesis_final;

select * from exam;

update exam set start_date = '2025-05-23 13:30:00'
where exam_id = 56;

select * from student where first_name = 'Biên';

select * from exam;

select student_id, date_of_birth from student
where course_id = 2;

select * from mark_student where exam_id = 56;

select round(avg(mark_exam),2) as average_mark_exam
from mark_student where exam_id = 56;

select
    case
        when mark_exam >= 0 and mark_exam < 4 then '0 to 4'
        when mark_exam >= 4 and mark_exam < 6 then '4 to 6'
        when mark_exam >= 6 and mark_exam < 8 then '6 to 8'
        when mark_exam >= 8 AND mark_exam <= 10 then '8 to 10'
        end as mark_range,
    count(*) AS count
from mark_student where exam_id = 56
group by mark_range;

select * from student where course_id = 2;