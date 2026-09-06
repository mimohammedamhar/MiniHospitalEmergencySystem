public class Main {

    public static void main(String[] args) {

        PatientBST patientBST = new PatientBST();
        EmergencyQueue emergencyQueue = new EmergencyQueue();

        System.out.println("========================================");
        System.out.println(" MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println(" EMERGENCY QUEUE TEST");
        System.out.println("========================================");

        /*
         * Create test patients.
         */
        Patient patient1 = new Patient(
                101,
                "Ahmed",
                24,
                "0771234567",
                "High Fever"
        );

        Patient patient2 = new Patient(
                102,
                "Nimal",
                35,
                "0712345678",
                "Chest Pain"
        );

        Patient patient3 = new Patient(
                103,
                "Fathima",
                29,
                "0755555555",
                "Headache"
        );

        /*
         * Register patients in the Patient BST first.
         */
        patientBST.insert(patient1);
        patientBST.insert(patient2);
        patientBST.insert(patient3);

        System.out.println();
        System.out.println("Registered patients:");

        patientBST.displayInOrder();

        /*
         * Test 1:
         * Display an empty queue.
         */
        System.out.println();
        System.out.println("TEST 1: DISPLAY EMPTY QUEUE");
        System.out.println("----------------------------------------");

        emergencyQueue.displayQueue();

        /*
         * Test 2:
         * Try to dequeue from an empty queue.
         */
        System.out.println();
        System.out.println("TEST 2: DEQUEUE EMPTY QUEUE");
        System.out.println("----------------------------------------");

        Patient emptyResult = emergencyQueue.dequeue();

        if (emptyResult == null) {
            System.out.println(
                    "Empty queue was handled successfully."
            );
        }

        /*
         * Test 3:
         * Add patients to the queue.
         */
        System.out.println();
        System.out.println("TEST 3: ENQUEUE PATIENTS");
        System.out.println("----------------------------------------");

        addPatientToQueue(emergencyQueue, patient1);
        addPatientToQueue(emergencyQueue, patient2);
        addPatientToQueue(emergencyQueue, patient3);

        /*
         * Test 4:
         * Display all waiting patients.
         */
        System.out.println();
        System.out.println("TEST 4: DISPLAY WAITING PATIENTS");

        emergencyQueue.displayQueue();

        /*
         * Test 5:
         * Attempt to add the same patient again.
         */
        System.out.println();
        System.out.println("TEST 5: DUPLICATE QUEUE ENTRY");
        System.out.println("----------------------------------------");

        addPatientToQueue(emergencyQueue, patient2);

        /*
         * Test 6:
         * View the next patient without removing the patient.
         */
        System.out.println();
        System.out.println("TEST 6: PEEK NEXT PATIENT");
        System.out.println("----------------------------------------");

        Patient nextPatient = emergencyQueue.peek();

        if (nextPatient != null) {
            System.out.println("Next patient for treatment:");
            nextPatient.displayPatient();
        }

        /*
         * Test 7:
         * Dequeue the first patient.
         *
         * Patient 101 should be removed first
         * because Patient 101 entered first.
         */
        System.out.println();
        System.out.println("TEST 7: DEQUEUE FIRST PATIENT");
        System.out.println("----------------------------------------");

        sendNextPatientForTreatment(emergencyQueue);

        System.out.println();
        System.out.println("QUEUE AFTER FIRST DEQUEUE");

        emergencyQueue.displayQueue();

        /*
         * Test 8:
         * Dequeue the remaining patients.
         */
        System.out.println();
        System.out.println("TEST 8: DEQUEUE REMAINING PATIENTS");
        System.out.println("----------------------------------------");

        sendNextPatientForTreatment(emergencyQueue);
        sendNextPatientForTreatment(emergencyQueue);

        /*
         * Test 9:
         * Confirm that the queue is now empty.
         */
        System.out.println();
        System.out.println("TEST 9: FINAL EMPTY QUEUE");
        System.out.println("----------------------------------------");

        emergencyQueue.displayQueue();

        System.out.println(
                "Final queue size: " + emergencyQueue.getSize()
        );

        /*
         * Test 10:
         * Try to dequeue one more time.
         */
        System.out.println();
        System.out.println("TEST 10: DEQUEUE AFTER QUEUE IS EMPTY");
        System.out.println("----------------------------------------");

        sendNextPatientForTreatment(emergencyQueue);
    }

    private static void addPatientToQueue(
            EmergencyQueue emergencyQueue,
            Patient patient) {

        boolean added = emergencyQueue.enqueue(patient);

        if (added) {
            System.out.println(
                    "Patient "
                            + patient.getPatientId()
                            + " - "
                            + patient.getPatientName()
                            + " added to the emergency queue."
            );
        }
    }

    private static void sendNextPatientForTreatment(
            EmergencyQueue emergencyQueue) {

        Patient patient = emergencyQueue.dequeue();

        if (patient != null) {
            System.out.println(
                    "Patient "
                            + patient.getPatientId()
                            + " - "
                            + patient.getPatientName()
                            + " removed from the queue."
            );

            System.out.println(
                    "The patient has been sent for treatment."
            );
        }
    }
}