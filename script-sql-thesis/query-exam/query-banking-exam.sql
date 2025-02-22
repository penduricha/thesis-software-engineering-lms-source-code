use project_thesis_final;

select b.question_java_core_id, b.code_sample, b.content_question
from bank_question_java_core b order by rand() limit 10;

select * from bank_test_case_java_core;

select code_sample from bank_question_java_core;
# select code_sample from bank_question_java_core
# where code_sample not like '%static%';
#
# update bank_question_java_core
# set code_sample = insert(code_sample, 8, 0, 'static')
# where code_sample not like '%static%';

select * from exam;

show columns from exam;

select * from question_java_core_exam;

