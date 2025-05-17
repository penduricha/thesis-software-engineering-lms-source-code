package drawuml;

public class StringUML {
    public static String getStringUML() {
        return "@startuml\n" +
                "\n" +
                "class Course {\n" +
                "    - Long courseID\n" +
                "    - String className\n" +
                "    - String courseName\n" +
                "    - int semester\n" +
                "    - int startYear\n" +
                "    - int endYear\n" +
                "\n" +
                "    - List<Lecture> lectures\n" +
                "    - List<Student> students\n" +
                "    - List<Exam> exams\n" +
                "\n" +
                "    + Course()\n" +
                "    + Course(String className, String courseName, int semester, int startYear, int endYear)\n" +
                "    + Long getCourseID()\n" +
                "    + void setCourseID(Long courseID)\n" +
                "    + String getClassName()\n" +
                "    + void setClassName(String className)\n" +
                "    + String getCourseName()\n" +
                "    + void setCourseName(String courseName)\n" +
                "    + int getSemester()\n" +
                "    + void setSemester(int semester)\n" +
                "    + int getStartYear()\n" +
                "    + void setStartYear(int startYear)\n" +
                "    + int getEndYear()\n" +
                "    + void setEndYear(int endYear)\n" +
                "    + String toString()\n" +
                "    + List<Exam> getExams()\n" +
                "}\n" +
                "\n" +
                "class Student {\n" +
                "    - String studentID\n" +
                "    - String lastName\n" +
                "    - String firstName\n" +
                "    - boolean gender\n" +
                "    - String password\n" +
                "    - LocalDate dateOfBirth\n" +
                "    - LocalDateTime dateTimeStartExam\n" +
                "\n" +
                "    - Course course\n" +
                "    - List<MarkStudent> markStudentList\n" +
                "    - List<CodeStorageSave> codeStorageSaves\n" +
                "    - List<Exam> exams\n" +
                "\n" +
                "    + Student()\n" +
                "    + String getStudentID()\n" +
                "    + void setStudentID(String studentID)\n" +
                "    + String getLastName()\n" +
                "    + void setLastName(String lastName)\n" +
                "    + String getFirstName()\n" +
                "    + void setFirstName(String firstName)\n" +
                "    + boolean isGender()\n" +
                "    + void setGender(boolean gender)\n" +
                "    + String getPassword()\n" +
                "    + void setPassword(String password)\n" +
                "    + LocalDate getDateOfBirth()\n" +
                "    + void setDateOfBirth(LocalDate dateOfBirth)\n" +
                "    + LocalDateTime getDateTimeStartExam()\n" +
                "    + void setDateTimeStartExam(LocalDateTime dateTimeStartExam)\n" +
                "    + Course getCourse()\n" +
                "    + void setCourse(Course course)\n" +
                "    + List<MarkStudent> getMarkStudentList()\n" +
                "    + void setMarkStudentList(List<MarkStudent> markStudentList)\n" +
                "    + List<CodeStorageSave> getCodeStorageSaves()\n" +
                "    + void setCodeStorageSaves(List<CodeStorageSave> codeStorageSaves)\n" +
                "}\n" +
                "\n" +
                "class Lecture {\n" +
                "    - String lectureID\n" +
                "    - String name\n" +
                "    - String email\n" +
                "    - String phoneNumber\n" +
                "    - String password\n" +
                "\n" +
                "    - List<Course> courses\n" +
                "\n" +
                "    + Lecture()\n" +
                "    + String getLectureID()\n" +
                "    + void setLectureID(String lectureID)\n" +
                "    + String getName()\n" +
                "    + void setName(String name)\n" +
                "    + String getEmail()\n" +
                "    + void setEmail(String email)\n" +
                "    + String getPhoneNumber()\n" +
                "    + void setPhoneNumber(String phoneNumber)\n" +
                "    + String getPassword()\n" +
                "    + void setPassword(String password)\n" +
                "}\n" +
                "\n" +
                "class Exam {\n" +
                "    - Long examID\n" +
                "    - String titleExam\n" +
                "    - String typeExam\n" +
                "    - String topicExam\n" +
                "    - boolean viewTable\n" +
                "    - boolean retakeExam\n" +
                "    - String scoringMethod\n" +
                "    - int duration\n" +
                "    - LocalDateTime startDate\n" +
                "    - LocalDateTime endDate\n" +
                "    - String linkExamPaper\n" +
                "    - String passwordExam\n" +
                "    - LocalDateTime createdAt\n" +
                "    - LocalDateTime updateAt\n" +
                "\n" +
                "    - Course course\n" +
                "    - List<QuestionJavaCoreExam> questionJavaCoreExams\n" +
                "    - MarkStudent markStudent\n" +
                "    - ExamJavaOop examJavaOop\n" +
                "    - List<Student> students\n" +
                "\n" +
                "    + Exam()\n" +
                "    + Long getExamID()\n" +
                "    + void setExamID(Long examID)\n" +
                "    + String getTitleExam()\n" +
                "    + void setTitleExam(String titleExam)\n" +
                "    + String getTypeExam()\n" +
                "    + void setTypeExam(String typeExam)\n" +
                "    + String getTopicExam()\n" +
                "    + void setTopicExam(String topicExam)\n" +
                "    + boolean isViewTable()\n" +
                "    + void setViewTable(boolean viewTable)\n" +
                "    + boolean isRetakeExam()\n" +
                "    + void setRetakeExam(boolean retakeExam)\n" +
                "    + String getScoringMethod()\n" +
                "    + void setScoringMethod(String scoringMethod)\n" +
                "    + int getDuration()\n" +
                "    + void setDuration(int duration)\n" +
                "    + LocalDateTime getStartDate()\n" +
                "    + void setStartDate(LocalDateTime startDate)\n" +
                "    + LocalDateTime getEndDate()\n" +
                "    + void setEndDate(LocalDateTime endDate)\n" +
                "    + String getLinkExamPaper()\n" +
                "    + void setLinkExamPaper(String linkExamPaper)\n" +
                "    + String getPasswordExam()\n" +
                "    + void setPasswordExam(String passwordExam)\n" +
                "    + LocalDateTime getCreatedAt()\n" +
                "    + void setCreatedAt(LocalDateTime createdAt)\n" +
                "    + LocalDateTime getUpdateAt()\n" +
                "    + void setUpdateAt(LocalDateTime updateAt)\n" +
                "    + List<QuestionJavaCoreExam> getQuestionJavaCoreExams()\n" +
                "    + void setQuestionJavaCoreExams(List<QuestionJavaCoreExam> questionJavaCoreExams)\n" +
                "    + Course getCourse()\n" +
                "    + void setCourse(Course course)\n" +
                "    + List<Student> getStudents()\n" +
                "    + void setStudents(List<Student> students)\n" +
                "    + void onCreated()\n" +
                "    + void onUpdated()\n" +
                "}\n" +
                "\n" +
                "class ExamJavaOop {\n" +
                "    - Long examJavaOopID\n" +
                "    - Exam exam\n" +
                "    - BankTestJavaOop bankTestJavaOop\n" +
                "\n" +
                "    + ExamJavaOop()\n" +
                "    + Long getExamJavaOopID()\n" +
                "    + void setExamJavaOopID(Long examJavaOopID)\n" +
                "    + Exam getExam()\n" +
                "    + void setExam(Exam exam)\n" +
                "    + BankTestJavaOop getBankTestJavaOop()\n" +
                "    + void setBankTestJavaOop(BankTestJavaOop bankTestJavaOop)\n" +
                "}\n" +
                "\n" +
                "class CodeStorageSave {\n" +
                "    - Long codeStorageSaveID\n" +
                "    - String codeSave\n" +
                "    - int indexQuestionSave\n" +
                "    - Student student\n" +
                "\n" +
                "    + CodeStorageSave()\n" +
                "    + Long getCodeStorageSaveID()\n" +
                "    + void setCodeStorageSaveID(Long codeStorageSaveID)\n" +
                "    + String getCodeSave()\n" +
                "    + void setCodeSave(String codeSave)\n" +
                "    + int getIndexQuestionSave()\n" +
                "    + void setIndexQuestionSave(int indexQuestionSave)\n" +
                "    + Student getStudent()\n" +
                "    + void setStudent(Student student)\n" +
                "}\n" +
                "\n" +
                "class MarkStudent {\n" +
                "    - Long markStudentID\n" +
                "    - double markExam\n" +
                "    - Student student\n" +
                "    - Exam exam\n" +
                "    - List<DetailMarkStudent> detailMarkStudents\n" +
                "\n" +
                "    + MarkStudent()\n" +
                "    + Long getMarkStudentID()\n" +
                "    + void setMarkStudentID(Long markStudentID)\n" +
                "    + double getMarkExam()\n" +
                "    + void setMarkExam(double markExam)\n" +
                "    + Student getStudent()\n" +
                "    + void setStudent(Student student)\n" +
                "    + Exam getExam()\n" +
                "    + void setExam(Exam exam)\n" +
                "    + List<DetailMarkStudent> getDetailMarkStudents()\n" +
                "    + void setDetailMarkStudents(List<DetailMarkStudent> detailMarkStudents)\n" +
                "}\n" +
                "\n" +
                "class DetailMarkStudent {\n" +
                "    - Long detailMarkStudentID\n" +
                "    - double detailMarkExam\n" +
                "    - LocalDateTime dateSubmitted\n" +
                "    - MarkStudent markStudent\n" +
                "    - List<ResultQuestionJavaCore> resultQuestionJavaCoreList\n" +
                "    - List<DetailAnswerJavaClass> detailAnswerJavaClassList\n" +
                "    - CodeSubmitJavaClass codeSubmitJavaClass\n" +
                "\n" +
                "    + DetailMarkStudent()\n" +
                "    + Long getDetailMarkStudentID()\n" +
                "    + void setDetailMarkStudentID(Long detailMarkStudentID)\n" +
                "    + double getDetailMarkExam()\n" +
                "    + void setDetailMarkExam(double detailMarkExam)\n" +
                "    + MarkStudent getMarkStudent()\n" +
                "    + void setMarkStudent(MarkStudent markStudent)\n" +
                "    + LocalDateTime getDateSubmitted()\n" +
                "    + void setDateSubmitted(LocalDateTime dateSubmitted)\n" +
                "    + List<ResultQuestionJavaCore> getResultQuestionJavaCoreList()\n" +
                "    + void setResultQuestionJavaCoreList(List<ResultQuestionJavaCore> resultQuestionJavaCoreList)\n" +
                "    + CodeSubmitJavaClass getCodeSubmitJavaClass()\n" +
                "    + void setCodeSubmitJavaClass(CodeSubmitJavaClass codeSubmitJavaClass)\n" +
                "    + List<DetailAnswerJavaClass> getDetailAnswerJavaClassList()\n" +
                "    + void setDetailAnswerJavaClassList(List<DetailAnswerJavaClass> detailAnswerJavaClassList)\n" +
                "    + String toString()\n" +
                "}\n" +
                "\n" +
                "class CodeSubmitJavaClass {\n" +
                "    - Long codeSubmitJavaClassID\n" +
                "    - String codeSubmitString\n" +
                "    - String suggest\n" +
                "    - DetailMarkStudent detailMarkStudent\n" +
                "\n" +
                "    + CodeSubmitJavaClass()\n" +
                "    + Long getCodeSubmitJavaClassID()\n" +
                "    + void setCodeSubmitJavaClassID(Long codeSubmitJavaClassID)\n" +
                "    + String getCodeSubmitString()\n" +
                "    + void setCodeSubmitString(String codeSubmitString)\n" +
                "    + String getSuggest()\n" +
                "    + void setSuggest(String suggest)\n" +
                "    + DetailMarkStudent getDetailMarkStudent()\n" +
                "    + void setDetailMarkStudent(DetailMarkStudent detailMarkStudent)\n" +
                "}\n" +
                "\n" +
                "class ResultQuestionJavaCore {\n" +
                "    - Long resultQuestionJavaCoreID\n" +
                "    - String codeStudentSubmitted\n" +
                "    - double markAchieve\n" +
                "    - List<OutputDebugResultJavaCore> outputDebugResultJavaCores\n" +
                "    - DetailMarkStudent detailMarkStudent\n" +
                "    - Long questionJavaCoreExamID\n" +
                "\n" +
                "    + ResultQuestionJavaCore()\n" +
                "    + Long getResultQuestionJavaCoreID()\n" +
                "    + void setResultQuestionJavaCoreID(Long resultQuestionJavaCoreID)\n" +
                "    + String getCodeStudentSubmitted()\n" +
                "    + void setCodeStudentSubmitted(String codeStudentSubmitted)\n" +
                "    + double getMarkAchieve()\n" +
                "    + void setMarkAchieve(double markAchieve)\n" +
                "    + DetailMarkStudent getDetailMarkStudent()\n" +
                "    + void setDetailMarkStudent(DetailMarkStudent detailMarkStudent)\n" +
                "    + Long getQuestionJavaCoreExamID()\n" +
                "    + void setQuestionJavaCoreExamID(Long questionJavaCoreExamID)\n" +
                "    + List<OutputDebugResultJavaCore> getOutputDebugResultJavaCores()\n" +
                "    + void setOutputDebugResultJavaCores(List<OutputDebugResultJavaCore> outputDebugResultJavaCores)\n" +
                "}\n" +
                "\n" +
                "class OutputDebugResultJavaCore {\n" +
                "    - Long outputDebugJavaCoreID\n" +
                "    - String outputCodeStudent\n" +
                "    - boolean fail\n" +
                "    - boolean syntaxError\n" +
                "    - ResultQuestionJavaCore resultQuestionJavaCore\n" +
                "\n" +
                "    + OutputDebugResultJavaCore()\n" +
                "    + Long getOutputDebugJavaCoreID()\n" +
                "    + void setOutputDebugJavaCoreID(Long outputDebugJavaCoreID)\n" +
                "    + String getOutputCodeStudent()\n" +
                "    + void setOutputCodeStudent(String outputCodeStudent)\n" +
                "    + boolean isFail()\n" +
                "    + void setFail(boolean fail)\n" +
                "    + boolean isSyntaxError()\n" +
                "    + void setSyntaxError(boolean syntaxError)\n" +
                "    + ResultQuestionJavaCore getResultQuestionJavaCore()\n" +
                "    + void setResultQuestionJavaCore(ResultQuestionJavaCore resultQuestionJavaCore)\n" +
                "    + void onCreate()\n" +
                "}\n" +
                "\n" +
                "class QuestionJavaCoreExam {\n" +
                "    - Long questionJavaCoreExamID\n" +
                "    - String contentQuestion\n" +
                "    - String codeSample\n" +
                "    - double score\n" +
                "    - boolean isMarkedFlag\n" +
                "    - Exam exam\n" +
                "    - BankQuestionJavaCore bankQuestionJavaCore\n" +
                "\n" +
                "    + QuestionJavaCoreExam()\n" +
                "    + Long getQuestionJavaCoreExamID()\n" +
                "    + void setQuestionJavaCoreExamID(Long questionJavaCoreExamID)\n" +
                "    + String getContentQuestion()\n" +
                "    + void setContentQuestion(String contentQuestion)\n" +
                "    + String getCodeSample()\n" +
                "    + void setCodeSample(String codeSample)\n" +
                "    + double getScore()\n" +
                "    + void setScore(double score)\n" +
                "    + boolean isMarkedFlag()\n" +
                "    + void setMarkedFlag(boolean markedFlag)a\n" +
                "    + Exam getExam()\n" +
                "    + void setExam(Exam exam)\n" +
                "    + BankQuestionJavaCore getBankQuestionJavaCore()\n" +
                "    + void setBankQuestionJavaCore(BankQuestionJavaCore bankQuestionJavaCore)\n" +
                "    + void onCreated()\n" +
                "}\n" +
                "\n" +
                "class BankQuestionJavaCore {\n" +
                "    - Long questionJavaCoreID\n" +
                "    - String contentQuestion\n" +
                "    - String codeSample\n" +
                "    - String codeRunToOutput\n" +
                "    - List<BankTestCaseJavaCore> bankTestCaseJavaCores\n" +
                "    - List<QuestionJavaCoreExam> questionJavaCoreExams\n" +
                "\n" +
                "    + BankQuestionJavaCore()\n" +
                "    + Long getQuestionJavaCoreID()\n" +
                "    + void setQuestionJavaCoreID(Long questionJavaCoreID)\n" +
                "    + String getContentQuestion()\n" +
                "    + void setContentQuestion(String contentQuestion)\n" +
                "    + String getCodeSample()\n" +
                "    + void setCodeSample(String codeSample)\n" +
                "    + String getCodeRunToOutput()\n" +
                "    + void setCodeRunToOutput(String codeRunToOutput)\n" +
                "    + List<BankTestCaseJavaCore> getBankTestCaseJavaCores()\n" +
                "    + void setBankTestCaseJavaCores(List<BankTestCaseJavaCore> bankTestCaseJavaCores)\n" +
                "    + List<QuestionJavaCoreExam> getQuestionJavaCoreExams()\n" +
                "    + void setQuestionJavaCoreExams(List<QuestionJavaCoreExam> questionJavaCoreExams)\n" +
                "}\n" +
                "\n" +
                "class BankTestCaseJavaCore {\n" +
                "    - Long bankTestCaseID\n" +
                "    - String inputTest\n" +
                "    - String outputExpect\n" +
                "    - String note\n" +
                "    - BankQuestionJavaCore bankQuestionJavaCore\n" +
                "\n" +
                "    + BankTestCaseJavaCore()\n" +
                "    + Long getBankTestCaseID()\n" +
                "    + void setBankTestCaseID(Long bankTestCaseID)\n" +
                "    + String getInputTest()\n" +
                "    + void setInputTest(String inputTest)\n" +
                "    + String getOutputExpect()\n" +
                "    + void setOutputExpect(String outputExpect)\n" +
                "    + String getNote()\n" +
                "    + void setNote(String note)\n" +
                "    + BankQuestionJavaCore getBankQuestionJavaCore()\n" +
                "    + void setBankQuestionJavaCore(BankQuestionJavaCore bankQuestionJavaCore)\n" +
                "    + String toString()\n" +
                "}\n" +
                "\n" +
                "class BankTestJavaOop {\n" +
                "    - Long bankTestJavaOopID\n" +
                "    - String nameTest\n" +
                "    - String descriptionTest\n" +
                "    - String imageDiagram\n" +
                "    - LocalDateTime createdAt\n" +
                "    - LocalDateTime updateAt\n" +
                "    - List<ExamJavaOop> listExamJavaOop\n" +
                "\n" +
                "    + BankTestJavaOop()\n" +
                "    + Long getBankTestJavaOopID()\n" +
                "    + void setBankTestJavaOopID(Long bankTestJavaOopID)\n" +
                "    + String getNameTest()\n" +
                "    + void setNameTest(String nameTest)\n" +
                "    + String getDescriptionTest()\n" +
                "    + void setDescriptionTest(String descriptionTest)\n" +
                "    + String getImageDiagram()\n" +
                "    + void setImageDiagram(String imageDiagram)\n" +
                "    + LocalDateTime getCreatedAt()\n" +
                "    + void setCreatedAt(LocalDateTime createdAt)\n" +
                "    + LocalDateTime getUpdateAt()\n" +
                "    + void setUpdateAt(LocalDateTime updateAt)\n" +
                "    + List<ExamJavaOop> getListExamJavaOop()\n" +
                "    + void setListExamJavaOop(List<ExamJavaOop> listExamJavaOop)\n" +
                "    + void onCreated()\n" +
                "    + void onUpdated()\n" +
                "}\n" +
                "\n" +
                "\n" +
                "Course \"0..1\" <-- \"1\" Student\n" +
                "Student \"1\" <-- \"0..1\" CodeStorageSave\n" +
                "Course \"*\" <-- \"*\" Lecture\n" +
                "Course \"1\" <-- \"0..*\" Exam\n" +
                "Exam \"1\" <-- \"0..1\" ExamJavaOop\n" +
                "BankTestJavaOop \"1\" <-- \"0..1\" ExamJavaOop\n" +
                "Exam \"1\" <-- \"0..1\" MarkStudent\n" +
                "Exam \"1\" <-- \"1..*\" QuestionJavaCoreExam\n" +
                "BankQuestionJavaCore \"1\" <-- \"1..*\" QuestionJavaCoreExam\n" +
                "BankQuestionJavaCore \"1\" <-- \"1..*\" BankTestCaseJavaCore\n" +
                "MarkStudent \"1\" <-- \"0..1\" DetailMarkStudent\n" +
                "DetailMarkStudent \"1\" <-- \"0..1\" CodeSubmitJavaClass\n" +
                "DetailMarkStudent \"1\" <-- \"1..*\" ResultQuestionJavaCore\n" +
                "ResultQuestionJavaCore \"1\" <-- \"1..*\" OutputDebugResultJavaCore\n" +
                "\n" +
                "@enduml";
    }
}
