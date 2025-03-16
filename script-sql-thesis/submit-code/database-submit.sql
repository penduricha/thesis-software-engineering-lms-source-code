use project_thesis_final;

select * from question_java_core_exam;

select * from mark_student;

select * from exam;

select * from detail_mark_student;

select * from result_question_java_core;

select * from output_debug_result_java_core;


# Get hết test cases từ record test case
select r.code_student_submitted, bqjc.code_run_to_output
from result_question_java_core r
left join question_java_core_exam qjce
on r.question_java_core_exam_id = qjce.question_java_core_exam_id
left join bank_question_java_core bqjc
on qjce.question_java_core_id = bqjc.question_java_core_id;
# left join bank_test_case_java_core btcjs
# on bqjc.question_java_core_id = btcjs.question_java_core_id;

# select output_code_student from result_question_java_core;
#
# -- Ktra bai ktra co lam lai hay ko
# select scoring_method from mark_student m
# left join exam e on m.exam_id = e.exam_id
# where e.retake_exam = true
# and m.student_id = '22662361' and e.exam_id = 24;
#
# # select is_marked_flag from
# # question_java_core_exam where question_java_core_exam_id = 7;
#
# select exam_id from exam;
# update question_java_core_exam set is_marked_flag = false
# where exam_id = 45;
#
# select * from result_question_java_core;
