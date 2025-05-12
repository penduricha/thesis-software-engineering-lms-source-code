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

update exam set start_date = '2025-04-29 9:40:00' where exam_id = 17;

select * from mark_student;

select * from student_access_exam;

select * from detail_mark_student;

select * from detail_answer_java_class;

select * from exam;

update exam set start_date = '2025-05-05 09:20:00' where exam_id = 19;

select * from mark_student where exam_id = 17;

select * from exam where exam_id = 17;

select * from detail_mark_student where mark_student_id = 12;

select * from detail_answer_java_class where detail_mark_student_id = 17;

select dajc.detail_answer_java_classid from mark_student ms
right join detail_mark_student dms
on ms.mark_student_id = dms.mark_student_id
right join detail_answer_java_class dajc
on dms.detail_mark_student_id = dajc.detail_mark_student_id
where ms.mark_student_id = 12;

select * from detail_answer_java_class;

select detail_answer_java_classid from detail_answer_java_class;

select * from exam;

update exam set start_date = '2025-05-10 10:00:00'
where exam_id = 34;

select exam_id from exam_java_oop where bank_test_java_oop_id = 9;

select * from detail_mark_student;

select * from detail_answer_java_class;

select * from exam;

select * from code_submit_java_class;
