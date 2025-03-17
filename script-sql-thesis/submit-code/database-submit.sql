use project_thesis_final;

select * from question_java_core_exam;

select * from mark_student;

select * from exam;

select * from detail_mark_student;

delete from mark_student where exam_id = 45;

select * from result_question_java_core;

select * from output_debug_result_java_core;

select * from output_debug_result_java_core;




# Get hết test cases từ record test case
select r.result_question_java_core_id, r.code_student_submitted, bqjc.code_run_to_output
from result_question_java_core r
left join question_java_core_exam qjce
on r.question_java_core_exam_id = qjce.question_java_core_exam_id
left join bank_question_java_core bqjc
on qjce.question_java_core_id = bqjc.question_java_core_id
where detail_mark_student_id = 3;

select * from result_question_java_core;

select * from mark_student;

select count(*) from result_question_java_core r
left join question_java_core_exam qjce
on r.question_java_core_exam_id = qjce.question_java_core_exam_id
left join bank_question_java_core bqjc
on qjce.question_java_core_id = bqjc.question_java_core_id
left join bank_test_case_java_core btcjc
on bqjc.question_java_core_id = btcjc.question_java_core_id
where result_question_java_core_id = 8;

select * from output_debug_result_java_core;

# Xóa record để ktra lại
delete from output_debug_result_java_core;

delete from result_question_java_core;

delete from detail_mark_student;

delete from mark_student where exam_id = 45;

#trich kq tuong ung voi bang test case
-- id = 30
select btcjc.output_expect from result_question_java_core rqjc
right join question_java_core_exam qjce
on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
right join bank_question_java_core bqjc
on qjce.question_java_core_id = bqjc.question_java_core_id
left join bank_test_case_java_core btcjc
on bqjc.question_java_core_id = btcjc.question_java_core_id
where result_question_java_core_id = 30;


select o.output_debug_java_core_id, o.output_code_student
from output_debug_result_java_core o
where result_question_java_core_id = 30;

update output_debug_result_java_core set fail = true
where result_question_java_core_id;


# select * from output_debug_result_java_core;


