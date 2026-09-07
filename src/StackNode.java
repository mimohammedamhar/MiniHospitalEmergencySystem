public class StackNode {

    TreatmentRecord treatmentRecord;
    StackNode next;

    public StackNode(TreatmentRecord treatmentRecord) {
        this.treatmentRecord = treatmentRecord;
        this.next = null;
    }
}