public class TreatmentStack {

    private StackNode top;
    private int size;

    public TreatmentStack() {
        this.top = null;
        this.size = 0;
    }

    /*
     * Checks whether the treatment stack is empty.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /*
     * Returns the number of treatment records
     * currently stored in the stack.
     */
    public int getSize() {
        return size;
    }

    /*
     * Adds a completed treatment record
     * to the top of the stack.
     *
     * Returns true if the record was added.
     * Returns false for a null or duplicate record.
     */
    public boolean push(TreatmentRecord treatmentRecord) {

        if (treatmentRecord == null) {
            System.out.println(
                    "Cannot add an empty treatment record."
            );
            return false;
        }

        /*
         * Prevent duplicate Treatment IDs.
         */
        if (contains(treatmentRecord.getTreatmentId())) {
            System.out.println(
                    "Treatment ID "
                            + treatmentRecord.getTreatmentId()
                            + " already exists in the stack."
            );
            return false;
        }

        StackNode newNode = new StackNode(treatmentRecord);

        /*
         * The new node points to the current top.
         */
        newNode.next = top;

        /*
         * The new node becomes the new top.
         */
        top = newNode;

        size++;
        return true;
    }

    /*
     * Removes and returns the most recently
     * completed treatment record.
     *
     * This follows the LIFO principle.
     */
    public TreatmentRecord pop() {

        if (isEmpty()) {
            System.out.println(
                    "Treatment history stack is empty. "
                            + "No treatment record can be removed."
            );
            return null;
        }

        TreatmentRecord removedRecord = top.treatmentRecord;

        /*
         * Move top to the next node.
         */
        top = top.next;

        size--;

        return removedRecord;
    }

    /*
     * Returns the latest treatment record
     * without removing it from the stack.
     */
    public TreatmentRecord peek() {

        if (isEmpty()) {
            System.out.println(
                    "Treatment history stack is empty. "
                            + "No latest treatment record is available."
            );
            return null;
        }

        return top.treatmentRecord;
    }

    /*
     * Searches for a Treatment ID in the stack.
     */
    public TreatmentRecord search(String treatmentId) {

        if (treatmentId == null || treatmentId.trim().isEmpty()) {
            return null;
        }

        StackNode currentNode = top;

        while (currentNode != null) {

            if (currentNode.treatmentRecord
                    .getTreatmentId()
                    .equalsIgnoreCase(treatmentId.trim())) {

                return currentNode.treatmentRecord;
            }

            currentNode = currentNode.next;
        }

        return null;
    }

    /*
     * Checks whether a Treatment ID already
     * exists in the stack.
     */
    public boolean contains(String treatmentId) {
        return search(treatmentId) != null;
    }

    /*
     * Displays all treatment records.
     *
     * Records are displayed from the most recent
     * treatment to the oldest treatment.
     */
    public void displayStack() {

        if (isEmpty()) {
            System.out.println(
                    "Treatment history stack is empty. "
                            + "No completed treatments are available."
            );
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println(" COMPLETED TREATMENT HISTORY");
        System.out.println("========================================");
        System.out.println(
                "Number of treatment records: " + size
        );
        System.out.println("----------------------------------------");

        StackNode currentNode = top;
        int position = 1;

        while (currentNode != null) {

            TreatmentRecord record =
                    currentNode.treatmentRecord;

            System.out.println(
                    "Stack Position    : " + position
            );

            System.out.println(
                    "Treatment ID      : "
                            + record.getTreatmentId()
            );

            System.out.println(
                    "Patient ID        : "
                            + record.getPatientId()
            );

            System.out.println(
                    "Patient Name      : "
                            + record.getPatientName()
            );

            System.out.println(
                    "Treatment Date    : "
                            + record.getTreatmentDate()
            );

            System.out.println(
                    "Doctor Name       : "
                            + record.getDoctorName()
            );

            System.out.println(
                    "Diagnosis         : "
                            + record.getDiagnosis()
            );

            System.out.println(
                    "Treatment Provided: "
                            + record.getTreatmentProvided()
            );

            if (currentNode == top) {
                System.out.println(
                        "Stack Status     : TOP - Latest Treatment"
                );
            } else {
                System.out.println(
                        "Stack Status     : Previous Treatment"
                );
            }

            System.out.println("----------------------------------------");

            currentNode = currentNode.next;
            position++;
        }

        System.out.println("Stack principle: LIFO");
        System.out.println("Last-In, First-Out");
        System.out.println("========================================");
    }
}