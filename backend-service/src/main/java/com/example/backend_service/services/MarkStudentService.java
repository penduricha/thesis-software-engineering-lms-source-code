package com.example.backend_service.services;

import com.example.backend_service.debugs.ExecuteJavaCoreResultQuestions;
import com.example.backend_service.dto.CodeSubmitAndCodeMain;
import com.example.backend_service.models.*;
import com.example.backend_service.repositories.*;
import com.example.backend_service.services.i_service.I_MarkStudentService;
import com.example.backend_service.services.i_service.I_ResultQuestionJavaCoreService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MarkStudentService implements I_MarkStudentService, I_ResultQuestionJavaCoreService {

    @PersistenceContext
    private EntityManager entityManager;

    private final MarkStudentRepository markStudentRepository;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    private final BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository;

    private final QuestionJavaCoreExamRepository questionJavaCoreExamRepository;

    private final ResultQuestionJavaCoreRepository resultQuestionJavaCoreRepository;

    private final DetailMarkStudentRepository detailMarkStudentRepository;

    private final OutputDebugResultJavaCoreRepository outputDebugResultJavaCoreRepository;

    public MarkStudentService(MarkStudentRepository markStudentRepository, StudentRepository studentRepository, ExamRepository examRepository, BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository, QuestionJavaCoreExamRepository questionJavaCoreExamRepository, ResultQuestionJavaCoreRepository resultQuestionJavaCoreRepository, DetailMarkStudentRepository detailMarkStudentRepository, OutputDebugResultJavaCoreRepository outputDebugResultJavaCoreRepository) {
        this.markStudentRepository = markStudentRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.bankQuestionJavaCoreRepository = bankQuestionJavaCoreRepository;
        this.questionJavaCoreExamRepository = questionJavaCoreExamRepository;
        this.resultQuestionJavaCoreRepository = resultQuestionJavaCoreRepository;
        this.detailMarkStudentRepository = detailMarkStudentRepository;
        this.outputDebugResultJavaCoreRepository = outputDebugResultJavaCoreRepository;
    }

    /*
        Các bước chấm điểm theo quy luật Transactional
        //tao record
        public DetailMarkStudent createMarkStudent(Map<String, Object> dataSubmitPost);

        //xuat code khi sv nop va insert record
        public DetailMarkStudent insertOutput_From_JavaCore_Code_Submitted(Map<String, Object> dataSubmitPost);

        //cham theo test-cases
        public DetailMarkStudent update_Fail_To_False_If_Same_Output(Map<String, Object> dataSubmitPost);

        //tinh diem thong qua cac test-cases
        public DetailMarkStudent setMarkAchieve_After_Output(Map<String, Object> dataSubmitPost);

     */

    @Override
    public DetailMarkStudent createMarkStudent(Map<String, Object> dataSubmitPost) throws JpaSystemException{
        String studentID = (String) dataSubmitPost.get("studentID");
        Integer examIDInt = (Integer) dataSubmitPost.get("examID");
        Long examID = examIDInt != null ? examIDInt.longValue() : null;
        List<Map<String, Object>> answerQuestions = (List<Map<String, Object>>) dataSubmitPost.get("answerQuestions");
        Student studentFound = studentRepository.findStudentByStudentID(studentID);
        Exam examFound = examRepository.findExamByExamID(examID);
        if(studentFound != null && examFound !=null ) {
            MarkStudent markStudent = new MarkStudent();
            //set attribute
            //set 0d, xem truong hop sv qua han
            markStudent.setMarkExam(0);

            //set relationship
            studentFound.getMarkStudentList().add(markStudent);
            markStudent.setStudent(studentFound);

            examFound.setMarkStudent(markStudent);
            markStudent.setExam(examFound);

            DetailMarkStudent detailMarkStudent = new DetailMarkStudent();
            detailMarkStudent.setDetailMarkExam(0);
            //date time submit
            detailMarkStudent.setDateSubmitted(LocalDateTime.now());

            //set relationship
            markStudent.getDetailMarkStudents().add(detailMarkStudent);
            detailMarkStudent.setMarkStudent(markStudent);

            if(!answerQuestions.isEmpty()) {
                for(Map<String, Object> objectMap : answerQuestions) {
                    Integer questionJavaCoreExamIDInt = (Integer) objectMap.get("questionJavaCoreExamID");
                    Long questionJavaCoreExamID = questionJavaCoreExamIDInt != null ? questionJavaCoreExamIDInt.longValue() : null;
                    String codeStudentSubmitted = (String) objectMap.get("codeStudentSubmitted");

                    QuestionJavaCoreExam questionJavaCoreExamFound =
                            questionJavaCoreExamRepository.findQuestionJavaCoreExamsByQuestionJavaCoreExamID(questionJavaCoreExamID);
                    if(questionJavaCoreExamFound !=null) {
                        ResultQuestionJavaCore resultQuestionJavaCore = new ResultQuestionJavaCore();
                        resultQuestionJavaCore.setCodeStudentSubmitted(codeStudentSubmitted);
                        resultQuestionJavaCore.setMarkAchieve(0);

                        //Chỗ này sẽ xuất code output ra và set vào, nma thư nghiệm trước thì cho null trước
                        //resultQuestionJavaCore.setOutputCodeStudent(null);

                        //set relationship
                        questionJavaCoreExamFound.setResultQuestionJavaCore(resultQuestionJavaCore);
                        resultQuestionJavaCore.setQuestionJavaCoreExam(questionJavaCoreExamFound);

                        detailMarkStudent.getResultQuestionJavaCoreList().add(resultQuestionJavaCore);
                        resultQuestionJavaCore.setDetailMarkStudent(detailMarkStudent);

                        //save kq da code vao, neu code bi fail syntax, save la fail syntax
                        //suy nghi ki out put có thể vi
                    }
                }
                markStudentRepository.save(markStudent);
                return detailMarkStudent;
            }
        }
        return null;
    }

    @Override
    public DetailMarkStudent insertOutput_From_JavaCore_Code_Submitted(Map<String, Object> dataSubmitPost) throws JpaSystemException {
        DetailMarkStudent detailMarkStudentCreate = createMarkStudent(dataSubmitPost);
        String studentID = (String) dataSubmitPost.get("studentID");
        if(detailMarkStudentCreate != null) {
            List<CodeSubmitAndCodeMain> queryListCode =
                    get_Code_Submitted_And_Code_Run_To_Output(detailMarkStudentCreate.getDetailMarkStudentID());
            if(!queryListCode.isEmpty()) {
                for(CodeSubmitAndCodeMain codeSubmitAndCodeMain: queryListCode) {
                    ExecuteJavaCoreResultQuestions executeJavaCoreResultQuestions =
                            new ExecuteJavaCoreResultQuestions();
                    executeJavaCoreResultQuestions.setStudentID(studentID);
                    executeJavaCoreResultQuestions.setCodeSubmitAndCodeMain(codeSubmitAndCodeMain);
                    executeJavaCoreResultQuestions.setSizeTestCases(
                            getSizeOfTestCases_JavaCore_By_Result_Question_JavaCore_ID
                                    (codeSubmitAndCodeMain.getResultQuestionJavaCoreID()));
                    try {
                        if(!executeJavaCoreResultQuestions
                                .getResultOutputCodeJava_From_CodeSubmitAndCodeMain()
                                .isEmpty()
                        ) {
                            ResultQuestionJavaCore resultQuestionJavaCoreFound =
                                    resultQuestionJavaCoreRepository.
                                            findResultQuestionJavaCoreByResultQuestionJavaCoreID(
                                                    codeSubmitAndCodeMain.getResultQuestionJavaCoreID());

                            if(resultQuestionJavaCoreFound != null) {
                                //set relationship
                                for(String outputCode: executeJavaCoreResultQuestions
                                        .getResultOutputCodeJava_From_CodeSubmitAndCodeMain()) {
                                    //Insert bang ghi
                                    OutputDebugResultJavaCore outputDebugResultJavaCore
                                            = new OutputDebugResultJavaCore();
                                    outputDebugResultJavaCore.setOutputCodeStudent(outputCode);
                                    //set cho sai het sau nay se tinh lại
                                    outputDebugResultJavaCore.setFail(true);

                                    outputDebugResultJavaCore.setSyntaxError(outputCode.equalsIgnoreCase("Syntax error"));

                                    //set relationship
                                    resultQuestionJavaCoreFound.getOutputDebugResultJavaCores()
                                            .add(outputDebugResultJavaCore);
                                    outputDebugResultJavaCore.setResultQuestionJavaCore(resultQuestionJavaCoreFound);
                                }
                                //save bang ghi
                                resultQuestionJavaCoreRepository.save(resultQuestionJavaCoreFound);
                                //after save
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return detailMarkStudentCreate;
        }
        return null;
    }

    //cap nhat trang thai ve false la dat diem test cases do
    @Override
    public DetailMarkStudent update_Fail_To_False_If_Same_Output(Map<String, Object> dataSubmitPost)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudentInsert = insertOutput_From_JavaCore_Code_Submitted(dataSubmitPost);
        if(detailMarkStudentInsert != null) {
            List<ResultQuestionJavaCore> resultQuestionJavaCoreList =
                    resultQuestionJavaCoreRepository.
                            getResultQuestionJavaCoresByDetailMarkStudent_DetailMarkStudentID(detailMarkStudentInsert
                                    .getDetailMarkStudentID());
            if(!resultQuestionJavaCoreList.isEmpty()) {
                for(ResultQuestionJavaCore resultQuestionJavaCore: resultQuestionJavaCoreList) {
                    List<Long> listOutputID_To_Update = new ArrayList<>();
                    try {
                        listOutputID_To_Update = getList_Output_Debug_JavaCoreID_To_Update_Pass(resultQuestionJavaCore.getResultQuestionJavaCoreID());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    if(!listOutputID_To_Update.isEmpty()) {
                        for(Long outputID: listOutputID_To_Update) {
                            OutputDebugResultJavaCore outputDebugResultJavaCoreFound =
                                    outputDebugResultJavaCoreRepository
                                            .findOutputDebugResultJavaCoreByOutputDebugJavaCoreID(outputID);
                            if(outputDebugResultJavaCoreFound != null) {
                                outputDebugResultJavaCoreFound.setFail(false);
                                //save record
                                outputDebugResultJavaCoreRepository.save(outputDebugResultJavaCoreFound);
                            }
                        }
                    }
                }
            }
            return detailMarkStudentInsert;
        }
        return null;
    }

    @Override
    public DetailMarkStudent setMarkAchieve_After_Output(Map<String, Object> dataSubmitPost)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudent = update_Fail_To_False_If_Same_Output(dataSubmitPost);
        if(detailMarkStudent != null) {
            List<ResultQuestionJavaCore> resultQuestionJavaCoreList =
                    resultQuestionJavaCoreRepository.
                            getResultQuestionJavaCoresByDetailMarkStudent_DetailMarkStudentID(detailMarkStudent
                                    .getDetailMarkStudentID());
            if(!resultQuestionJavaCoreList.isEmpty()) {
                for(ResultQuestionJavaCore resultQuestionJavaCore: resultQuestionJavaCoreList) {
                    List<Boolean> status_Output_To_SetMark =
                            outputDebugResultJavaCoreRepository
                            .get_List_Status_TestCases_By_ResultQuestionJavaCoreID
                            (resultQuestionJavaCore.getResultQuestionJavaCoreID());
                    if(!status_Output_To_SetMark.isEmpty()) {
                        if(status_Output_To_SetMark.contains(true)) {
                            resultQuestionJavaCore.setMarkAchieve(0);
                            resultQuestionJavaCoreRepository.save(resultQuestionJavaCore);
                        } else {
                            Double getScoreFromQuestionJavaCoreExam =
                                    resultQuestionJavaCoreRepository
                                    .getScoreQuestionJavaCoreExamBy_Result_Question_JavaCore_ID
                                    (resultQuestionJavaCore.getResultQuestionJavaCoreID());
                            if(getScoreFromQuestionJavaCoreExam != null) {
                                resultQuestionJavaCore.setMarkAchieve(getScoreFromQuestionJavaCoreExam);
                                resultQuestionJavaCoreRepository.save(resultQuestionJavaCore);
                            }
                        }
                    }
                }
            }
            return detailMarkStudent;
        }
        return null;
    }

    @Override
    public DetailMarkStudent setDetailMarkStudent_After_SetMarkAchieve(Map<String, Object> dataSubmitPost)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudent = setMarkAchieve_After_Output(dataSubmitPost);
        if(detailMarkStudent != null) {
            Double totalMark = resultQuestionJavaCoreRepository.
            getTotal_MarkAchieve_By_Detail_MarkStudent(detailMarkStudent.getDetailMarkStudentID());
            if(totalMark != null) {
                //xét thêm điều kiện scoring method là tính toán trung bình, max, bài ktra cuối dùng
                List<ResultQuestionJavaCore> resultQuestionJavaCoreList =
                        resultQuestionJavaCoreRepository.
                                getResultQuestionJavaCoresByDetailMarkStudent_DetailMarkStudentID(detailMarkStudent
                                        .getDetailMarkStudentID());
                for(ResultQuestionJavaCore resultQuestionJavaCore: resultQuestionJavaCoreList) {
                    resultQuestionJavaCore.getDetailMarkStudent().setDetailMarkExam(totalMark);
                    resultQuestionJavaCoreRepository.save(resultQuestionJavaCore);
                    return resultQuestionJavaCore.getDetailMarkStudent();
                }
//                //detailMarkStudent.setDetailMarkExam(totalMark);
//                return detailMarkStudentRepository.save(detailMarkStudent);
            }
            return null;
        }
        return null;
    }

    @Override
    public MarkStudent setMarkExam_After_SetDetailMarkExam(Map<String, Object> dataSubmitPost)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudent = setDetailMarkStudent_After_SetMarkAchieve(dataSubmitPost);
        if(detailMarkStudent != null) {
            String studentID = (String) dataSubmitPost.get("studentID");
            Integer examIDInt = (Integer) dataSubmitPost.get("examID");
            Long examID = examIDInt != null ? examIDInt.longValue() : null;
            String scoringMethod = markStudentRepository
                        .getScoringMethod_If_Student_Had_MarkExam_And_Exam_Retake(studentID, examID);
                 // If the exam can only be taken once, do not allow retakes
            if (scoringMethod == null || scoringMethod.isEmpty()) {
                    //Trường hợp nếu làm ktra lan dau tien và ko làm lại
                    MarkStudent markStudent = markStudentRepository
                            .findMarkStudentByMarkStudentID(detailMarkStudentRepository
                                    .getMarkStudentID_By_DetailMarkStudentID(detailMarkStudent
                                            .getDetailMarkStudentID()));
                    //bai ktra lan dau
                    if(markStudent !=null) {
//                        markStudent.setMarkExam(detailMarkStudent.getDetailMarkExam());
//                        return markStudentRepository.save(markStudent);
                        detailMarkStudent.getMarkStudent().setMarkExam(detailMarkStudent.getDetailMarkExam());
                        detailMarkStudentRepository.save(detailMarkStudent);
                        return detailMarkStudent.getMarkStudent();
                    }
            }
//            else {
//                if(scoringMethod.equalsIgnoreCase("Max")) {
//
//                }
//            }
        }
        return null;
    }


    @Override
    public MarkStudent setMarkIs_Zero_If_Exam_Overdue(String studentID, Long examID) throws JpaSystemException {
        Student studentFound = studentRepository.findStudentByStudentID(studentID);
        Exam examFound = examRepository.findExamByExamID(examID);
        MarkStudent markStudentFound = markStudentRepository.findMarkStudentByExam_ExamID(examID);
        if(studentFound != null && examFound !=null && markStudentFound == null) {
            MarkStudent markStudent = new MarkStudent();
            //set attribute
            //set 0d, xem truong hop sv qua han
            markStudent.setMarkExam(0);
            //set relationship
            studentFound.getMarkStudentList().add(markStudent);
            markStudent.setStudent(studentFound);
            examFound.setMarkStudent(markStudent);
            markStudent.setExam(examFound);
            return markStudentRepository.save(markStudent);
        }
        return null;
    }

    @Override
    public List<CodeSubmitAndCodeMain> get_Code_Submitted_And_Code_Run_To_Output(Long detailMarkStudentID)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudentFound = detailMarkStudentRepository.
                findDetailMarkStudentByDetailMarkStudentID(detailMarkStudentID);
        if(detailMarkStudentFound != null) {
            List<Map<String, Object>> queryList =
                    resultQuestionJavaCoreRepository
                            .get_Code_Submitted_And_Code_Run_To_Output(detailMarkStudentID).stream()
                    .map(originalMap -> {
                        Map<String, Object> newMap = new HashMap<>();
                        newMap.put("resultQuestionJavaCoreID", originalMap.get("result_question_java_core_id"));
                        newMap.put("codeStudentSubmitted", originalMap.get("code_student_submitted"));
                        newMap.put("codeRunToOutput", originalMap.get("code_run_to_output"));
                        return newMap;
                    }).toList();
            if(!queryList.isEmpty()) {
                List<CodeSubmitAndCodeMain> list = new ArrayList<>();
                for(Map<String, Object> objectMap: queryList) {
                    Long resultQuestionJavaCoreID = (Long) objectMap.get("resultQuestionJavaCoreID");
                    //Long resultQuestionJavaCoreID = resultQuestionJavaCoreIDInt != null ? resultQuestionJavaCoreIDInt.longValue() : null;
                    String codeStudentSubmitted = (String) objectMap.get("codeStudentSubmitted");
                    String codeRunToOutput = (String) objectMap.get("codeRunToOutput");

                    CodeSubmitAndCodeMain codeSubmitAndCodeMain = new CodeSubmitAndCodeMain();
                    codeSubmitAndCodeMain.setResultQuestionJavaCoreID(resultQuestionJavaCoreID);
                    codeSubmitAndCodeMain.setCodeRunToOutput(codeRunToOutput);
                    codeSubmitAndCodeMain.setCodeStudentSubmitted(codeStudentSubmitted);
                    list.add(codeSubmitAndCodeMain);
                }
                return list;
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @Override
    public int getSizeOfTestCases_JavaCore_By_Result_Question_JavaCore_ID(Long resultQuestionJavaCoreID)
            throws JpaSystemException {
        return resultQuestionJavaCoreRepository
                .getSizeOfTestCases_JavaCore_By_Result_Question_JavaCore_ID(resultQuestionJavaCoreID);
    }

    @Override
    public List<String> getTestCasesByResultQuestionJavaCoreID(Long resultQuestionJavaCoreID)
            throws JpaSystemException {
        ResultQuestionJavaCore resultQuestionJavaCoreFound =
                resultQuestionJavaCoreRepository.findResultQuestionJavaCoreByResultQuestionJavaCoreID(resultQuestionJavaCoreID);
        if(resultQuestionJavaCoreFound != null) {
            return resultQuestionJavaCoreRepository
                    .getTestCasesByResultQuestionJavaCoreID(
                            resultQuestionJavaCoreID
                    );
        }
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> getOutputCode_By_ResultQuestionJavaCoreID(Long resultQuestionJavaCoreID)
            throws JpaSystemException {
        ResultQuestionJavaCore resultQuestionJavaCoreFound =
                resultQuestionJavaCoreRepository.findResultQuestionJavaCoreByResultQuestionJavaCoreID(resultQuestionJavaCoreID);
        if(resultQuestionJavaCoreFound != null) {
            List<Map<String, Object>> queryList = resultQuestionJavaCoreRepository
                    .getOutputCode_By_ResultQuestionJavaCoreID(
                           resultQuestionJavaCoreFound.getResultQuestionJavaCoreID()
                    );
            if(!queryList.isEmpty()) {
                return queryList.stream()
                        .map(originalMap -> {
                            Map<String, Object> newMap = new HashMap<>();
                            newMap.put("outputDebugJavaCoreID", originalMap.get("output_debug_java_core_id"));
                            newMap.put("outputCodeStudent", originalMap.get("output_code_student"));
                            return newMap;
                        }).toList();
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Long> getList_Output_Debug_JavaCoreID_To_Update_Pass(Long resultQuestionJavaCoreID)
            throws Exception {
        List<String> listTestCases = getTestCasesByResultQuestionJavaCoreID(resultQuestionJavaCoreID);

        //System.out.println("List test cases: "+listTestCases);

        List<Map<String, Object>> listMapOutputCode =
                getOutputCode_By_ResultQuestionJavaCoreID(resultQuestionJavaCoreID);

        //System.out.println("List map output code: "+listMapOutputCode);

        List<Long> outputDebugJavaCoreID_To_Update_Pass = new ArrayList<>();

        if(!listMapOutputCode.isEmpty() && !listTestCases.isEmpty()) {
            if(listTestCases.size() == listMapOutputCode.size()) {
                for(Map<String, Object> objectMap: listMapOutputCode) {
                    if(listTestCases.contains((String) objectMap.get("outputCodeStudent"))) {
                        Long outputDebugJavaCoreID = (Long) objectMap.get("outputDebugJavaCoreID");
                        outputDebugJavaCoreID_To_Update_Pass.add(outputDebugJavaCoreID);
                    }
                }
                return outputDebugJavaCoreID_To_Update_Pass;
            } else {
                throw new Exception("List of test cases is not matched, please edit.");
            }
        }
        return new ArrayList<>();
    }
}
