public class Patient {

    private int patientId;
    private String patientName;
    private int age;
    private String contactNumber;
    private String medicalCondition;
    private VisitHistory visitHistory;

    public Patient(int patientId,
                   String patientName,
                   int age,
                   String contactNumber,
                   String medicalCondition) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;

        // Every patient has a separate visit history.
        this.visitHistory = new VisitHistory();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public VisitHistory getVisitHistory() {
        return visitHistory;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public void displayPatient() {
        System.out.println("----------------------------------------");
        System.out.println("Patient ID       : " + patientId);
        System.out.println("Patient Name     : " + patientName);
        System.out.println("Age              : " + age);
        System.out.println("Contact Number   : " + contactNumber);
        System.out.println("Medical Condition: " + medicalCondition);
        System.out.println("----------------------------------------");
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId
                + " | Name: " + patientName
                + " | Age: " + age
                + " | Contact: " + contactNumber
                + " | Condition: " + medicalCondition;
    }
}