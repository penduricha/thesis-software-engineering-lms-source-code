use project_thesis_final;

select * from question_java_core_exam;



select code_save, index_question_save
from code_storage_save where student_id  ='22662361';

delete from code_storage_save;

select * from student where student_id ='22662361';

select * from bank_question_java_core b
left join bank_test_case_java_core btcjc
on b.question_java_core_id = btcjc.question_java_core_id
where b.question_java_core_id = 1;

select * from question_java_core_exam where exam_id = 29;


-- lay test case
select
        btcjc.question_java_core_id,
        btcjc.code_run_to_output,
        btc.input_test,
        btc.output_expect,
        q.question_java_core_exam_id
from question_java_core_exam q
right join bank_question_java_core btcjc
on q.question_java_core_id = btcjc.question_java_core_id
left join bank_test_case_java_core btc
    on btcjc.question_java_core_id = btc.question_java_core_id
where q.question_java_core_exam_id = 171;



select * from result_question_java_core;

delete from mark_student;

select * from student where student_id = '22662361';

