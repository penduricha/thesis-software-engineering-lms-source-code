use project_thesis_final;

-- Lấy ds lớp (khóa học) theo gv
select * from course;

select * from lecture;

select * from map_lecture_course;

select c.course_id, c.class_name, c.course_name, c.semester, c.start_year, c.end_year
from course c
left join map_lecture_course mlc on c.course_id = mlc.course_id
left join lecture l on mlc.lecture_id = l.lecture_id
where l.lecture_id = '1120050';


select version();





