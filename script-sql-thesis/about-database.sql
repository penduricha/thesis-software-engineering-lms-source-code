use project_thesis_final;

-- Trích id, pass sinh viên
select s.student_id, s.password from student s;

select l.lecture_id, l.password from lecture l where lecture_id = '1120009';

select * from student where student_id = '22662361';

select * from lecture;

show columns from lecture;