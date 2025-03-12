package awswer.map.hospital_patient;

public class Patient {
    private String name;
    private int age;
    private String medicalRecordNumber;
    private Hospital hospital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Patient() {
        this.name = "None";
        this.age = 0;
        this.medicalRecordNumber = "None";
        this.hospital = new Hospital();
    }
}
