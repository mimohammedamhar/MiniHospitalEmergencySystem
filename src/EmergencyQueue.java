public class EmergencyQueue {

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /*
     * Checks whether the emergency queue is empty.
     */
    public boolean isEmpty() {
        return front == null;
    }

    /*
     * Returns the number of patients currently waiting.
     */
    public int getSize() {
        return size;
    }

    /*
     * Adds a patient to the rear of the emergency queue.
     *
     * Returns true when the patient is added successfully.
     * Returns false when the patient is null or is already waiting.
     */
    public boolean enqueue(Patient patient) {

        if (patient == null) {
            System.out.println(
                    "Cannot add an empty patient record to the queue."
            );
            return false;
        }

        /*
         * Prevent the same patient from being added
         * to the emergency queue more than once.
         */
        if (contains(patient.getPatientId())) {
            System.out.println(
                    "Patient ID "
                            + patient.getPatientId()
                            + " is already in the emergency queue."
            );
            return false;
        }

        QueueNode newNode = new QueueNode(patient);

        /*
         * If the queue is empty, the new node becomes
         * both the front and the rear.
         */
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {

            /*
             * Connect the current rear node to the new node.
             * Then move rear to the new node.
             */
            rear.next = newNode;
            rear = newNode;
        }

        size++;
        return true;
    }

    /*
     * Removes and returns the patient at the front
     * of the emergency queue.
     *
     * This follows the FIFO principle.
     */
    public Patient dequeue() {

        if (isEmpty()) {
            System.out.println(
                    "Emergency queue is empty. "
                            + "No patient is waiting for treatment."
            );
            return null;
        }

        Patient removedPatient = front.patient;

        // Move the front reference to the next node.
        front = front.next;

        /*
         * If the queue becomes empty after removing the patient,
         * rear must also be changed to null.
         */
        if (front == null) {
            rear = null;
        }

        size--;
        return removedPatient;
    }

    /*
     * Returns the next patient without removing
     * the patient from the queue.
     */
    public Patient peek() {

        if (isEmpty()) {
            System.out.println(
                    "Emergency queue is empty. "
                            + "No patient is waiting for treatment."
            );
            return null;
        }

        return front.patient;
    }

    /*
     * Searches the queue to check whether a patient
     * is already waiting.
     */
    public boolean contains(int patientId) {

        QueueNode currentNode = front;

        while (currentNode != null) {

            if (currentNode.patient.getPatientId() == patientId) {
                return true;
            }

            currentNode = currentNode.next;
        }

        return false;
    }

    /*
     * Displays all patients from front to rear.
     */
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println(
                    "Emergency queue is empty. "
                            + "No patients are currently waiting."
            );
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println(" EMERGENCY PATIENT WAITING QUEUE");
        System.out.println("========================================");
        System.out.println("Number of waiting patients: " + size);
        System.out.println("----------------------------------------");

        QueueNode currentNode = front;
        int position = 1;

        while (currentNode != null) {

            Patient patient = currentNode.patient;

            System.out.println("Queue Position   : " + position);
            System.out.println(
                    "Patient ID       : " + patient.getPatientId()
            );
            System.out.println(
                    "Patient Name     : " + patient.getPatientName()
            );
            System.out.println(
                    "Age              : " + patient.getAge()
            );
            System.out.println(
                    "Contact Number   : " + patient.getContactNumber()
            );
            System.out.println(
                    "Medical Condition: "
                            + patient.getMedicalCondition()
            );

            if (currentNode == front) {
                System.out.println("Queue Status     : FRONT - Next Patient");
            } else if (currentNode == rear) {
                System.out.println("Queue Status     : REAR - Latest Patient");
            } else {
                System.out.println("Queue Status     : Waiting");
            }

            System.out.println("----------------------------------------");

            currentNode = currentNode.next;
            position++;
        }

        System.out.println("Queue principle: FIFO");
        System.out.println("First-In, First-Out");
        System.out.println("========================================");
    }
}