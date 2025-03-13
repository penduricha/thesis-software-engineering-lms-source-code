use project_thesis_final;

select * from question_java_core_exam;

select * from mark_student;

select * from exam;

select * from detail_mark_student;

select * from result_question_java_core;

select output_code_student from result_question_java_core;

-- Ktra bai ktra co lam lai hay ko
select scoring_method from mark_student m
left join exam e on m.exam_id = e.exam_id
where e.retake_exam = true
and m.student_id = '22662361' and e.exam_id = 24;

# select is_marked_flag from
# question_java_core_exam where question_java_core_exam_id = 7;

select exam_id from exam;

