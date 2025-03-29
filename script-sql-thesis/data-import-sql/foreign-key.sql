alter table result_question_java_core
add constraint result_question_java_core_id
foreign key (question_java_core_exam_id)
references question_java_core_exam (question_java_core_exam_id)
on delete cascade;
-- hoặc ON DELETE SET NULL tùy thuộc vào yêu cầu

-- kiểm tra ds khóa ngoại
select
    CONSTRAINT_NAME
from
    information_schema.KEY_COLUMN_USAGE
where
    TABLE_NAME = 'result_question_java_core'
  and REFERENCED_TABLE_NAME = 'question_java_core_exam';


-- gỡ khóa ngoại
# ALTER TABLE result_question_java_core
#     DROP FOREIGN KEY FKgh7p98bpexv09v5ml4dfssxni;

select * from question_java_core_exam;
alter table question_java_core_exam drop column result_question_java_core_id;

ALTER TABLE question_java_core_exam
    DROP FOREIGN KEY result_question_java_core_id;

-- Tiếp tục với các tên khác nếu cần
select * from result_question_java_core;

