-- set utf-8 cho các bảng sau
use project_thesis_final;
alter table bank_test_case_java_core convert to character set utf8mb4 collate utf8mb4_unicode_ci;
alter table question_java_core_exam convert to character set utf8mb4 collate utf8mb4_unicode_ci;
alter table bank_question_java_core convert to character set utf8mb4 collate utf8mb4_unicode_ci;
alter table student convert to character set utf8mb4 collate utf8mb4_unicode_ci;

alter table result_question_java_core convert to character set utf8mb4 collate utf8mb4_unicode_ci;
alter table output_debug_result_java_core convert to character set utf8mb4 collate utf8mb4_unicode_ci;
alter table bank_test_java_oop convert to character set utf8mb4 collate utf8mb4_unicode_ci;
-- alter field
alter table code_storage_save
modify code_save longtext character set utf8mb4 collate utf8mb4_unicode_ci;

alter table detail_answer_java_class convert to character set utf8mb4 collate utf8mb4_unicode_ci;