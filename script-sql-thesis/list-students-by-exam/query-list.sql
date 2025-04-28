use project_thesis_final;

select * from exam;

# update exam set start_date ='2025-04-20 19:30:00' where exam_id = 8;

select * from mark_student;

-- truy van ds diem sv by exam id
select
       s.student_id,
       s.last_name,
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

select * from lecture;

# update lecture set email = 'thangvannguyen@gmail.com', name ='Nguyễn Văn Thắng'
# where lecture_id = '1120050';

select b.bank_test_java_oop_id
from bank_test_java_oop b order by rand() limit 1;

select * from exam;

select * from exam_java_oop;

update exam set start_date = '2025-04-26 20:40:00' where exam_id = 14;

select * from mark_student;

select * from detail_mark_student;