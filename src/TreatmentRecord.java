public class TreatmentRecord {

    private String treatmentId;
    private int patientId;
    private String patientName;
    private String treatmentDate;
    private String doctorName;
    private String diagnosis;
    private String treatmentProvided;

    public TreatmentRecord(
            String treatmentId,
            int patientId,
            String patientName,
            String treatmentDate,
            String doctorName,
            String diagnosis,
            String treatmentProvided) {

        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDate = treatmentDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatmentProvided = treatmentProvided;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentDate() {
        return treatmentDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatmentProvided() {
        return treatmentProvided;
    }

    public void setTreatmentDate(String treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatmentProvided(String treatmentProvided) {
        this.treatmentProvided = treatmentProvided;
    }

    public void displayTreatmentRecord() {

        System.out.println("----------------------------------------");
        System.out.println("Treatment ID      : " + treatmentId);
        System.out.println("Patient ID        : " + patientId);
        System.out.println("Patient Name      : " + patientName);
        System.out.println("Treatment Date    : " + treatmentDate);
        System.out.println("Doctor Name       : " + doctorName);
        System.out.println("Diagnosis         : " + diagnosis);
        System.out.println("Treatment Provided: " + treatmentProvided);
        System.out.println("----------------------------------------");
    }

    @Override
    public String toString() {

        return "Treatment ID: " + treatmentId
                + " | Patient ID: " + patientId
                + " | Patient Name: " + patientName
                + " | Date: " + treatmentDate
                + " | Doctor: " + doctorName
                + " | Diagnosis: " + diagnosis
                + " | Treatment: " + treatmentProvided;
    }
}