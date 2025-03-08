use project_thesis_final;

select * from exam where exam_id = 17;

select count(*) as number_questions
from question_java_core_exam where exam_id = 17;

select question_java_core_exam_id,
       code_sample,
       content_question
from question_java_core_exam where exam_id = 17;

select * from question_java_core_exam where question_java_core_exam_id = 57;

select input_test, output_expect, note from bank_test_case_java_core btc
right join bank_question_java_core bq on btc.question_java_core_id = bq.question_java_core_id
left join question_java_core_exam q on bq.question_java_core_id = q.question_java_core_id
where q.question_java_core_exam_id = 57;
