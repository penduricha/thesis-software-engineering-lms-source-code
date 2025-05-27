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

select
    case
        when mark_exam >= 0 and mark_exam < 1 then '0 to 1'
        when mark_exam >= 1 and mark_exam < 2 then '1 to 2'
        when mark_exam >= 2 and mark_exam < 3 then '2 to 3'
        when mark_exam >= 3 and mark_exam < 4 then '3 to 4'
        when mark_exam >= 4 and mark_exam < 5 then '4 to 5'
        when mark_exam >= 5 and mark_exam < 6 then '5 to 6'
        when mark_exam >= 6 and mark_exam < 7 then '6 to 7'
        when mark_exam >= 7 and mark_exam < 8 then '7 to 8'
        when mark_exam >= 8 and mark_exam < 9 then '8 to 9'
        when mark_exam >= 9 and mark_exam <= 10 then '9 to 10'
        end as mark_range,
    count(*) AS count
from mark_student where exam_id = 56
group by mark_range;

select * from student where course_id = 2;

with mark_ranges as (
    select '0 to 1' as mark_range, 0 as range_start, 1 as range_end union all
    select '1 to 2', 1, 2 union all
    select '2 to 3', 2, 3 union all
    select '3 to 4', 3, 4 union all
    select '4 to 5', 4, 5 union all
    select '5 to 6', 5, 6 union all
    select '6 to 7', 6, 7 union all
    select '7 to 8', 7, 8 union all
    select '8 to 9', 8, 9 union all
    select '9 to 10', 9, 10
)

select
    m.mark_range,
    coalesce(count(s.mark_exam), 0) AS count
from
    mark_ranges m
        left join
    mark_student s on
        s.mark_exam >= m.range_start and
        s.mark_exam < m.range_end and s.exam_id = 56
group by
    m.mark_range
order by
    m.range_start;

select * from exam;

update exam set start_date = '2025-05-27 16:30:00' where exam_id = 64;

select student_id, date_of_birth from student where course_id = 2;
# tru 22662361 mk la 1234 con lai la mk la ddmmyyyy.

select * from mark_student;