use project_thesis_final;

select * from question_java_core_exam;

update question_java_core_exam set
score = 1 where score = 0;

select retake_exam, scoring_method
from exam where exam_id = 29;

select * from code_storage_save;
