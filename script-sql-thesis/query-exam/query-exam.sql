use project_thesis_final;

select * from course;

select * from exam;

select e.exam_id,
       title_exam,
       type_exam,
       topic_exam,
       retake_exam,
       scoring_method,
       start_date,
       end_date,
       link_exam_paper, password_exam, retake_exam, scoring_method from course c
left join project_thesis_final.exam e
on c.course_id = e.course_id
where e.course_id = 2;