use project_thesis_final;

# lay thông tin sv + tên khóa học

select s.student_id, date_of_birth, last_name, first_name, gender, c.course_name, c.class_name
from student s
left join course c on s.course_id = c.course_id
where student_id = '22662361';

select * from course;

select * from student_access_exam;

select * from exam;

update exam set start_date = '2025-04-03 18:00:00'
where exam_id = 12;

select * from student;