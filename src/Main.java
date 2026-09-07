public class Main {

    public static void main(String[] args) {

        TreatmentStack treatmentStack =
                new TreatmentStack();

        System.out.println("========================================");
        System.out.println(" MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println(" TREATMENT STACK TEST");
        System.out.println("========================================");

        /*
         * Create sample treatment records.
         */
        TreatmentRecord treatment1 =
                new TreatmentRecord(
                        "T001",
                        101,
                        "Ahmed",
                        "07/09/2026",
                        "Dr. Silva",
                        "Viral Fever",
                        "Medication and Rest"
                );

        TreatmentRecord treatment2 =
                new TreatmentRecord(
                        "T002",
                        102,
                        "Nimal",
                        "07/09/2026",
                        "Dr. Perera",
                        "Chest Muscle Pain",
                        "Pain Relief Medication"
                );

        TreatmentRecord treatment3 =
                new TreatmentRecord(
                        "T003",
                        103,
                        "Fathima",
                        "07/09/2026",
                        "Dr. Fernando",
                        "Migraine",
                        "Migraine Medication"
                );

        /*
         * Test 1:
         * Display an empty stack.
         */
        System.out.println();
        System.out.println("TEST 1: DISPLAY EMPTY STACK");
        System.out.println("----------------------------------------");

        treatmentStack.displayStack();

        /*
         * Test 2:
         * Pop from an empty stack.
         */
        System.out.println();
        System.out.println("TEST 2: POP EMPTY STACK");
        System.out.println("----------------------------------------");

        TreatmentRecord emptyResult =
                treatmentStack.pop();

        if (emptyResult == null) {
            System.out.println(
                    "Empty stack was handled successfully."
            );
        }

        /*
         * Test 3:
         * Push treatment records.
         */
        System.out.println();
        System.out.println("TEST 3: PUSH TREATMENT RECORDS");
        System.out.println("----------------------------------------");

        addTreatmentRecord(
                treatmentStack,
                treatment1
        );

        addTreatmentRecord(
                treatmentStack,
                treatment2
        );

        addTreatmentRecord(
                treatmentStack,
                treatment3
        );

        /*
         * Test 4:
         * Display treatment records.
         */
        System.out.println();
        System.out.println("TEST 4: DISPLAY TREATMENT STACK");

        treatmentStack.displayStack();

        /*
         * Test 5:
         * Attempt to add a duplicate Treatment ID.
         */
        System.out.println();
        System.out.println("TEST 5: DUPLICATE TREATMENT ID");
        System.out.println("----------------------------------------");

        TreatmentRecord duplicateTreatment =
                new TreatmentRecord(
                        "T002",
                        104,
                        "Duplicate Patient",
                        "07/09/2026",
                        "Dr. Test",
                        "Test Diagnosis",
                        "Test Treatment"
                );

        addTreatmentRecord(
                treatmentStack,
                duplicateTreatment
        );

        /*
         * Test 6:
         * View the latest treatment without removing it.
         */
        System.out.println();
        System.out.println("TEST 6: PEEK LATEST TREATMENT");
        System.out.println("----------------------------------------");

        TreatmentRecord latestRecord =
                treatmentStack.peek();

        if (latestRecord != null) {
            System.out.println(
                    "Latest treatment record:"
            );
            latestRecord.displayTreatmentRecord();
        }

        /*
         * Test 7:
         * Search for an existing Treatment ID.
         */
        System.out.println();
        System.out.println("TEST 7: SEARCH EXISTING TREATMENT");
        System.out.println("----------------------------------------");

        searchAndDisplayTreatment(
                treatmentStack,
                "T002"
        );

        /*
         * Test 8:
         * Search for a missing Treatment ID.
         */
        System.out.println();
        System.out.println("TEST 8: SEARCH MISSING TREATMENT");
        System.out.println("----------------------------------------");

        searchAndDisplayTreatment(
                treatmentStack,
                "T999"
        );

        /*
         * Test 9:
         * Remove the most recently completed treatment.
         *
         * T003 must be removed first because
         * it was added last.
         */
        System.out.println();
        System.out.println("TEST 9: POP LATEST TREATMENT");
        System.out.println("----------------------------------------");

        removeLatestTreatment(treatmentStack);

        System.out.println();
        System.out.println("STACK AFTER FIRST POP");

        treatmentStack.displayStack();

        /*
         * Test 10:
         * Remove the remaining records.
         */
        System.out.println();
        System.out.println("TEST 10: POP REMAINING RECORDS");
        System.out.println("----------------------------------------");

        removeLatestTreatment(treatmentStack);
        removeLatestTreatment(treatmentStack);

        /*
         * Test 11:
         * Confirm that the stack is empty.
         */
        System.out.println();
        System.out.println("TEST 11: FINAL EMPTY STACK");
        System.out.println("----------------------------------------");

        treatmentStack.displayStack();

        System.out.println(
                "Final stack size: "
                        + treatmentStack.getSize()
        );

        /*
         * Test 12:
         * Pop again after the stack is empty.
         */
        System.out.println();
        System.out.println("TEST 12: POP AFTER STACK IS EMPTY");
        System.out.println("----------------------------------------");

        removeLatestTreatment(treatmentStack);
    }

    private static void addTreatmentRecord(
            TreatmentStack treatmentStack,
            TreatmentRecord treatmentRecord) {

        boolean added =
                treatmentStack.push(treatmentRecord);

        if (added) {
            System.out.println(
                    "Treatment "
                            + treatmentRecord.getTreatmentId()
                            + " for Patient "
                            + treatmentRecord.getPatientId()
                            + " added to the stack successfully."
            );
        }
    }

    private static void removeLatestTreatment(
            TreatmentStack treatmentStack) {

        TreatmentRecord removedRecord =
                treatmentStack.pop();

        if (removedRecord != null) {

            System.out.println(
                    "Treatment "
                            + removedRecord.getTreatmentId()
                            + " removed from the stack."
            );

            System.out.println(
                    "Patient: "
                            + removedRecord.getPatientId()
                            + " - "
                            + removedRecord.getPatientName()
            );
        }
    }

    private static void searchAndDisplayTreatment(
            TreatmentStack treatmentStack,
            String treatmentId) {

        TreatmentRecord treatmentRecord =
                treatmentStack.search(treatmentId);

        if (treatmentRecord == null) {

            System.out.println(
                    "No treatment record found with ID "
                            + treatmentId
                            + "."
            );

        } else {

            System.out.println(
                    "Treatment record found:"
            );

            treatmentRecord.displayTreatmentRecord();
        }
    }
}