package com.example.backend_service.data_init;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private final JdbcTemplate jdbcTemplate;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//
    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() throws JpaSystemException {
        //set up sql
        //set utf-8;
        List<String> codeSQLSetUtf8List = Arrays.asList(
                "ALTER TABLE bank_test_case_java_core CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
                "ALTER TABLE question_java_core_exam CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
                "ALTER TABLE bank_test_case_java_core CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci", // Duplicate entry, consider removing
                "ALTER TABLE result_question_java_core CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
                "ALTER TABLE output_debug_result_java_core CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
                "ALTER TABLE bank_test_java_oop CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
                "ALTER TABLE code_storage_save MODIFY code_save LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
                "ALTER TABLE detail_answer_java_class CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
                "ALTER TABLE code_submit_java_class CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci"
        );

        for (String sqlStatement : codeSQLSetUtf8List) {
            try {
                jdbcTemplate.execute(sqlStatement);
                //System.out.println("Executed: " + sqlStatement); // Log success
            } catch (Exception e) {
                System.err.println("Error executing: " + sqlStatement + " - " + e.getMessage());
            }
        }


        // Kiểm tra xem bảng đã có dữ liệu hay chưa (tùy chọn)
        Integer countCourse = jdbcTemplate.queryForObject("select count(*) from course", Integer.class);
        if (countCourse == null || countCourse == 0) {
            // Thực hiện các lệnh INSERT
            List<String> insertStatementCourses = Arrays.asList(
                    "INSERT INTO course (course_id, class_name, course_name, end_year, semester, start_year) VALUES (1, 'DHKTPM20ATT', 'Object-oriented programming', 2026, 1, 2025)",
                    "INSERT INTO course (course_id, class_name, course_name, end_year, semester, start_year) VALUES (2, 'DHKTPM20BTT', 'Object-oriented programming', 2026, 1, 2025)",
                    "INSERT INTO course (course_id, class_name, course_name, end_year, semester, start_year) VALUES (3, 'DHHTTT20ATT', 'Object-oriented programming', 2026, 1, 2025)",
                    "INSERT INTO course (course_id, class_name, course_name, end_year, semester, start_year) VALUES (4, 'DHHTTT20BTT', 'Object-oriented programming', 2026, 1, 2025)",
                    "INSERT INTO course (course_id, class_name, course_name, end_year, semester, start_year) VALUES (5, 'DHKTPM20A', 'Object-oriented programming', 2026, 1, 2025)",
                    "INSERT INTO course (course_id, class_name, course_name, end_year, semester, start_year) VALUES (6, 'DHKTPM20B', 'Object-oriented programming', 2026, 1, 2025)"
            );

            for (String insertStatement : insertStatementCourses) {
                try {
                    jdbcTemplate.execute(insertStatement);
                    //System.out.println("Executed: " + insertStatement); // Log success
                } catch (Exception e) {
                    System.err.println("Error executing: " + insertStatement + " - " + e.getMessage());
                }
            }
        }

        Integer countLecturer = jdbcTemplate.queryForObject("select count(*) from lecturer", Integer.class);
        if(countLecturer == null || countLecturer == 0) {
            List<String> insertStatementLecturer = Arrays.asList(
                    "INSERT INTO lecturer (lecturer_id, email, name, password, phone_number) VALUES ('1120009', 'tonlongphuoc@iuh.edu.vn', 'Tôn Long Phước', 'd4eb69ce03cbd7a023223f45137b09ddf570b0c5512988cb935b9e0f97f8cfc82c016e2372f683eacd44ebe7d803d06b148e177f9371d56daa6de63cd94f6e9f', '0933227272')",
                    "INSERT INTO lecturer (lecturer_id, email, name, password, phone_number) VALUES ('1120013', 'ngthihoangkhanh@gmail.com', 'Nguyễn Thị Hoàng Khánh', '524d9487ce2045a3501438b7952ec409d683b2e13713a269a0349bbc00f131b7f97435c6fa5d0a162d56b3c62c051081b52c08740fc098e226e93e09d1e5357a', '0903444795')",
                    "INSERT INTO lecturer (lecturer_id, email, name, password, phone_number) VALUES ('1120050', 'thangvannguyen@gmail.com', 'Nguyễn Văn Thắng', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db', '0918153584')"
            );

            for (String insertStatement : insertStatementLecturer) {
                try {
                    jdbcTemplate.execute(insertStatement);
                    //System.out.println("Executed: " + insertStatement); // Log success
                } catch (Exception e) {
                    System.err.println("Error executing: " + insertStatement + " - " + e.getMessage());
                }
            }
        }

        Integer countMapLecturerCourse = jdbcTemplate.queryForObject("select count(*) from map_lecturer_course", Integer.class);
        if(countMapLecturerCourse == null || countMapLecturerCourse == 0) {
            List<String> insertStatementMapLecturerCourse = Arrays.asList(
                    "INSERT INTO map_lecturer_course (lecturer_id, course_id) VALUES ('1120009', 1)",
                    "INSERT INTO map_lecturer_course (lecturer_id, course_id) VALUES ('1120013', 3)",
                    "INSERT INTO map_lecturer_course (lecturer_id, course_id) VALUES ('1120013', 4)",
                    "INSERT INTO map_lecturer_course (lecturer_id, course_id) VALUES ('1120050', 2)",
                    "INSERT INTO map_lecturer_course (lecturer_id, course_id) VALUES ('1120050', 5)",
                    "INSERT INTO map_lecturer_course (lecturer_id, course_id) VALUES ('1120050', 6)"
            );
            for (String insertStatement : insertStatementMapLecturerCourse) {
                try {
                    jdbcTemplate.execute(insertStatement);
                    //System.out.println("Executed: " + insertStatement); // Log success
                } catch (Exception e) {
                    System.err.println("Error executing: " + insertStatement + " - " + e.getMessage());
                }
            }
        }

        Integer countStudent = jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
        if (countStudent == null || countStudent == 0) {
            List<String> insertStatementStudent = Arrays.asList(
                    "INSERT INTO student (student_id, date_of_birth, first_name, gender, last_name, course_id, password, date_time_start_exam) VALUES ('21094561', '2003-02-19', 'Huy', true, 'Đặng Hồ Đức', 3, 'dcf0e8ad4668130c3118e97f6f47e935bf1e50bf316955563050a020a086652c4d108aad60332aa8eecfe5ab5d145465db941ebc167345ea6e4f523659e68bc8', null)",
                    "INSERT INTO student (student_id, date_of_birth, first_name, gender, last_name, course_id, password, date_time_start_exam) VALUES ('21130831', '2003-11-25', 'Hưng', true, 'Lê Vũ', 2, '5da7a5a5fc8a5559044fdeeba6de92f5e29a63dae024f96c2bd351fb9db1dbf416ab33cdb1b811239baa1cef143e6e0e00fe58fc9e1fb9fabddc136a758c8f7f', null)",
                    "INSERT INTO student (student_id, date_of_birth, first_name, gender, last_name, course_id, password, date_time_start_exam) VALUES ('22662361', '2004-06-23', 'Biên', true, 'Trần Thanh', 2, 'dcf0e8ad4668130c3118e97f6f47e935bf1e50bf316955563050a020a086652c4d108aad60332aa8eecfe5ab5d145465db941ebc167345ea6e4f523659e68bc8', null)",
                    "INSERT INTO student (student_id, date_of_birth, first_name, gender, last_name, course_id, password, date_time_start_exam) VALUES ('22662381', '2004-12-17', 'Hoàng', true, 'Nguyễn Vũ Minh', 1, '3da407e58c8a9b43e05ae4bea9e134c65925ac059de27bf5afa497a6a2dbbf3dd3f95cfab879d6871b05ab467eae25c0a653cc5a547904a06a423869773ddebb', null)"
            );

            for (String insertStatement : insertStatementStudent) {
                try {
                    jdbcTemplate.execute(insertStatement);
                    //System.out.println("Executed: " + insertStatement); // Log success
                } catch (Exception e) {
                    System.err.println("Error executing: " + insertStatement + " - " + e.getMessage());
                }
            }
        }

        //cac de ktra nen upload chay

//        Integer countJavaCoreTest = jdbcTemplate.queryForObject("select count(*) from bank_question_java_core", Integer.class);
//        if(countJavaCoreTest == null || countJavaCoreTest == 0) {
//            String insertJavaCoreTest = JavaCoreInsertQuestion.getStringSQLInsert();
//            String insertTestCase = JavaCoreInsertQuestion.getStringSQLInsertTestCase();
//            jdbcTemplate.execute(insertJavaCoreTest);
//            jdbcTemplate.execute(insertTestCase);
//        }

//        Integer countJavaClassTest = jdbcTemplate.queryForObject("select count(*) from bank_test_java_oop", Integer.class);
//        if(countJavaClassTest == null || countJavaClassTest == 0) {
//            String insertJavaClassTest = JavaClassTestInsert.getSQLInsertJavaClass();
//            jdbcTemplate.execute(insertJavaClassTest);
//        }
    }
}
