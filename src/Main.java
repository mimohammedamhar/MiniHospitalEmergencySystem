public class Main {

    public static void main(String[] args) {

        PatientBST patientBST = new PatientBST();

        System.out.println("========================================");
        System.out.println(" MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println(" PATIENT BST TEST");
        System.out.println("========================================");

        Patient patient1 = new Patient(
                50,
                "Ahmed",
                24,
                "0771234567",
                "High Fever"
        );

        Patient patient2 = new Patient(
                30,
                "Nimal",
                35,
                "0712345678",
                "Chest Pain"
        );

        Patient patient3 = new Patient(
                70,
                "Fathima",
                29,
                "0755555555",
                "Headache"
        );

        Patient patient4 = new Patient(
                20,
                "Sunil",
                48,
                "0766666666",
                "Breathing Difficulty"
        );

        Patient patient5 = new Patient(
                40,
                "Ayesha",
                19,
                "0788888888",
                "Injury"
        );

        Patient patient6 = new Patient(
                60,
                "Kamal",
                42,
                "0722222222",
                "Stomach Pain"
        );

        Patient patient7 = new Patient(
                80,
                "Sara",
                31,
                "0744444444",
                "Back Pain"
        );

        /*
         * Test 1:
         * Insert patients into the BST.
         */
        System.out.println();
        System.out.println("TEST 1: INSERT PATIENTS");
        System.out.println("----------------------------------------");

        insertPatient(patientBST, patient1);
        insertPatient(patientBST, patient2);
        insertPatient(patientBST, patient3);
        insertPatient(patientBST, patient4);
        insertPatient(patientBST, patient5);
        insertPatient(patientBST, patient6);
        insertPatient(patientBST, patient7);

        /*
         * Test 2:
         * Try to insert a duplicate Patient ID.
         */
        System.out.println();
        System.out.println("TEST 2: INSERT DUPLICATE PATIENT");
        System.out.println("----------------------------------------");

        Patient duplicatePatient = new Patient(
                50,
                "Duplicate Patient",
                20,
                "0700000000",
                "Test Condition"
        );

        insertPatient(patientBST, duplicatePatient);

        /*
         * Test 3:
         * Display patients using inorder traversal.
         */
        System.out.println();
        System.out.println("TEST 3: INORDER TRAVERSAL");

        patientBST.displayInOrder();

        /*
         * Test 4:
         * Display the BST tree structure.
         */
        System.out.println();
        System.out.println("TEST 4: BST STRUCTURE");

        patientBST.displayTreeStructure();

        /*
         * Test 5:
         * Search for an existing patient.
         */
        System.out.println();
        System.out.println("TEST 5: SEARCH EXISTING PATIENT");
        System.out.println("----------------------------------------");

        searchAndDisplayPatient(patientBST, 40);

        /*
         * Test 6:
         * Search for a patient who does not exist.
         */
        System.out.println();
        System.out.println("TEST 6: SEARCH MISSING PATIENT");
        System.out.println("----------------------------------------");

        searchAndDisplayPatient(patientBST, 99);

        /*
         * Test 7:
         * Delete a leaf node.
         *
         * Patient 20 does not have any children.
         */
        System.out.println();
        System.out.println("TEST 7: DELETE LEAF NODE");
        System.out.println("----------------------------------------");

        deletePatient(patientBST, 20);
        patientBST.displayInOrder();

        /*
         * Test 8:
         * Delete a node with two children.
         *
         * Patient 50 is the root node.
         */
        System.out.println();
        System.out.println("TEST 8: DELETE NODE WITH TWO CHILDREN");
        System.out.println("----------------------------------------");

        deletePatient(patientBST, 50);
        patientBST.displayInOrder();

        /*
         * Test 9:
         * Try to delete a patient who does not exist.
         */
        System.out.println();
        System.out.println("TEST 9: DELETE MISSING PATIENT");
        System.out.println("----------------------------------------");

        deletePatient(patientBST, 999);

        /*
         * Final tree structure.
         */
        System.out.println();
        System.out.println("FINAL BST STRUCTURE");

        patientBST.displayTreeStructure();
    }

    private static void insertPatient(
            PatientBST patientBST,
            Patient patient) {

        boolean inserted = patientBST.insert(patient);

        if (inserted) {
            System.out.println(
                    "Patient "
                            + patient.getPatientId()
                            + " - "
                            + patient.getPatientName()
                            + " inserted successfully."
            );
        } else {
            System.out.println(
                    "Patient ID "
                            + patient.getPatientId()
                            + " already exists. Insertion failed."
            );
        }
    }

    private static void searchAndDisplayPatient(
            PatientBST patientBST,
            int patientId) {

        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println(
                    "No patient found with ID " + patientId + "."
            );
        } else {
            System.out.println("Patient found successfully:");
            patient.displayPatient();
        }
    }

    private static void deletePatient(
            PatientBST patientBST,
            int patientId) {

        boolean deleted = patientBST.delete(patientId);

        if (deleted) {
            System.out.println(
                    "Patient ID "
                            + patientId
                            + " deleted successfully."
            );
        } else {
            System.out.println(
                    "Cannot delete. No patient found with ID "
                            + patientId
                            + "."
            );
        }
    }
}