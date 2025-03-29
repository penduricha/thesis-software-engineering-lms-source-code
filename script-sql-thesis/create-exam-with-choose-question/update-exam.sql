use project_thesis_final;

select * from exam;

select * from bank_test_case_java_core;

select * from question_java_core_exam
where exam_id = 29;

-- lấy bank question java core mà question_java_core_id ko nằm trong'

select * from bank_question_java_core;

select * from exam;

update exam set start_date = '2025-03-26 12:30:00'
where exam_id = 66;

select * from detail_mark_student;
