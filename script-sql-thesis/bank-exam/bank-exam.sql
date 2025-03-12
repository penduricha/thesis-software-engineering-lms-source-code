use project_thesis_final;

select * from bank_question_java_core
where question_java_core_id = 1;

select btc.bank_test_case_id, btc.input_test, btc.output_expect
from bank_question_java_core bq
left join
bank_test_case_java_core btc
on bq.question_java_core_id = btc.question_java_core_id
where bq.question_java_core_id = 1;
# bq.question_java_core_id thay doi duoc
# cap nhat test case neu thay sai

# update bank_test_case_java_core
# set input_test = '4.5 15.5' where bank_test_case_id = 336;
select * from bank_question_java_core
where question_java_core_id = 19;

select *
from bank_test_case_java_core
where question_java_core_id = 20;

