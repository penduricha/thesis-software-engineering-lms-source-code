use project_thesis_final;

select * from code_storage_save;

select code_save from code_storage_save
where student_id = '22662361' and index_question_save = 1;

select
    c.code_storage_save_id,
    c.code_save,
    c.index_question_save,
    c.student_id from code_storage_save c
where student_id = '22662361' and index_question_save = 2;

update code_storage_save set code_save = 'a= 1'
where student_id = '22662361' and index_question_save = 1;