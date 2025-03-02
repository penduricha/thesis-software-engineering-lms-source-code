use project_thesis_final;

select * from code_storage_save;

select code_save from code_storage_save
where student_id = '22662361' and index_question_save = 0;

select
    c.code_storage_save_id,
    c.code_save,
    c.index_question_save,
    c.student_id from code_storage_save c
where student_id = '22662361' and index_question_save = 0;

update code_storage_save set code_save = 'a= 1'
where student_id = '22662361' and index_question_save = 1;

delete from code_storage_save;

select * from student_access_exam;

delete from student_access_exam;

select * from student where student_id = '22662361';

select s.date_time_start_exam, sae.exam_id from student s
left join student_access_exam sae
on s.student_id = sae.student_id
where s.student_id = '22662361';

select
    sae.exam_id,
    (e.duration - TIMESTAMPDIFF(minute , s.date_time_start_exam, NOW()))
    as remain_minutes
from student s
left join
    student_access_exam sae ON s.student_id = sae.student_id
right join
    exam e ON sae.exam_id = e.exam_id
where s.student_id = '22662361';

select * from code_storage_save;
delete from code_storage_save;