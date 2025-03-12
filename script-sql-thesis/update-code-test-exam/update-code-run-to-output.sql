use project_thesis_final;

select * from bank_question_java_core where question_java_core_id = 1;

select * from bank_test_case_java_core btcjc
right join bank_question_java_core b
on btcjc.question_java_core_id = b.question_java_core_id
where b.question_java_core_id = 13;

select * from bank_test_case_java_core
where question_java_core_id = 2;