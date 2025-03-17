package com.example.backend_service.services;

import com.example.backend_service.debugs.ExecuteJavaCoreResultQuestions;
import com.example.backend_service.dto.CodeSubmitAndCodeMain;
import com.example.backend_service.models.*;
import com.example.backend_service.repositories.*;
import com.example.backend_service.services.i_service.I_MarkStudentService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MarkStudentService implements I_MarkStudentService {

    private final MarkStudentRepository markStudentRepository;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    private final BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository;

    private final QuestionJavaCoreExamRepository questionJavaCoreExamRepository;

    private final ResultQuestionJavaCoreRepository resultQuestionJavaCoreRepository;

    private final DetailMarkStudentRepository detailMarkStudentRepository;

    public MarkStudentService(MarkStudentRepository markStudentRepository, StudentRepository studentRepository, ExamRepository examRepository, BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository, QuestionJavaCoreExamRepository questionJavaCoreExamRepository, ResultQuestionJavaCoreRepository resultQuestionJavaCoreRepository, DetailMarkStudentRepository detailMarkStudentRepository) {
        this.markStudentRepository = markStudentRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.bankQuestionJavaCoreRepository = bankQuestionJavaCoreRepository;
        this.questionJavaCoreExamRepository = questionJavaCoreExamRepository;
        this.resultQuestionJavaCoreRepository = resultQuestionJavaCoreRepository;
        this.detailMarkStudentRepository = detailMarkStudentRepository;
    }

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
}
