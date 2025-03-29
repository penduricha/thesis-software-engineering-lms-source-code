use project_thesis_final;

-- trích xuất bảng test-case chi tiết.
-- field gồm
-- input, output expect, output student, status
-- cho detail_mark_student_id = 24;

select * from result_question_java_core where detail_mark_student_id = 24;

select btcjc.input_test, btcjc.output_expect from result_question_java_core rqjc
right join question_java_core_exam qjce
on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
right join bank_question_java_core bqjc
on qjce.question_java_core_id = bqjc.question_java_core_id
left join bank_test_case_java_core btcjc
on bqjc.question_java_core_id = btcjc.question_java_core_id
where result_question_java_core_id = 47;

select o.output_debug_java_core_id, o.output_code_student
from output_debug_result_java_core o
where result_question_java_core_id = 47;

# nối 2 record
with first_query as (
    select btcjc.input_test, btcjc.output_expect
    from result_question_java_core rqjc
             right join question_java_core_exam qjce
                        on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
             right join bank_question_java_core bqjc
                        on qjce.question_java_core_id = bqjc.question_java_core_id
             left join bank_test_case_java_core btcjc
                       on bqjc.question_java_core_id = btcjc.question_java_core_id
    where rqjc.result_question_java_core_id = 47
),
     second_query as (
         select o.output_debug_java_core_id, o.output_code_student, o.fail
         from output_debug_result_java_core o
         where o.result_question_java_core_id = 47
     )
select fq.input_test, fq.output_expect, sq.output_code_student, sq.fail
from first_query fq
join second_query sq
on length(fq.output_expect) = length(sq.output_code_student);

select * from result_question_java_core where detail_mark_student_id = 24;

# trích xuất ds câu hỏi trong bài ktra theo detail_mark_student_id = 24;
select r.result_question_java_core_id, q.content_question, r.mark_achieve, q.score
from result_question_java_core r
right join question_java_core_exam q
on r.question_java_core_exam_id = q.question_java_core_exam_id
where detail_mark_student_id = 24;