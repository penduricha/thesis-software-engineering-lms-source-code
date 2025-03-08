use project_thesis_final;

-- Trích id, pass sinh viên
# select s.student_id, s.password from student s;
#
# select l.lecture_id, l.name from lecture l where lecture_id = '1120009';
#
# select * from student where student_id = '22662361';
#
# select * from lecture;
#
# show columns from lecture;
#
# select * from course;
#
# -- update password after patch
# update student s set s.password =
# 'b69061dd15009d66539c7adf59ba6a6b8ce073a511f5ed4b70a26881bf69d8d8f4712e48b952fb718ed557b79334fc7979e1e6d50ad2aed039f1fad2d425f8b7'
# where s.student_id = '21094561';
#
# select * from student where student_id = '21094561';
#
#
# update student s set s.password =
# 'dcf0e8ad4668130c3118e97f6f47e935bf1e50bf316955563050a020a086652c4d108aad60332aa8eecfe5ab5d145465db941ebc167345ea6e4f523659e68bc8'
# where s.student_id = '22662361';
#
# select * from lecture l where l.lecture_id = '1120050';

update student set course_id = 2
where student_id = '22662361';