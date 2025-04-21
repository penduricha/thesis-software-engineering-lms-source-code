use project_thesis_final;

select * from exam;

# update exam set start_date ='2025-04-20 19:30:00' where exam_id = 8;

select * from mark_student;

-- truy van ds diem sv by exam id
select s.last_name,
       s.first_name,
       s.gender,
       s.date_of_birth,
       m.mark_exam
from mark_student m
right join student s
on m.student_id = s.student_id
right join exam e
on m.exam_id = e.exam_id
where e.exam_id = 7;

select * from exam;