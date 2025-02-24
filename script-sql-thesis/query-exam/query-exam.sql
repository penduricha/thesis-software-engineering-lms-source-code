use project_thesis_final;

select * from course;

select * from exam;

select password_exam from exam;

select * from mark_student;

select * from question_java_core_exam;

select e.exam_id,
       e.title_exam,
       e.type_exam,
       e.topic_exam,
       e.retake_exam,
       e.scoring_method,
        e.duration,
       e.start_date,
       e.end_date,
       e.created_at,
       e.link_exam_paper,
       e.password_exam,
       l.name
from course c
         left join map_lecture_course mlc on c.course_id = mlc.course_id
         left join lecture l on mlc.lecture_id = l.lecture_id
         left join project_thesis_final.exam e on c.course_id = e.course_id
where e.course_id = 2 order by e.title_exam;

update exam set title_exam = 'Java week 3'
where exam_id = 12;

select * from exam where date(start_date) = '2025-02-24';

select * from student where course_id = 2;

# query exams by date and lectureID
select
    e.exam_id,
    e.title_exam,
    e.type_exam,
    c.class_name
from course c
         left join map_lecture_course mlc on c.course_id = mlc.course_id
         left join lecture l on mlc.lecture_id = l.lecture_id
         right join exam e on c.course_id = e.course_id
where l.lecture_id = '1120050' and date(start_date) = '2025-02-24';
