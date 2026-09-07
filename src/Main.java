import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final PatientBST patientBST =
            new PatientBST();

    private static final EmergencyQueue emergencyQueue =
            new EmergencyQueue();

    private static final TreatmentStack treatmentStack =
            new TreatmentStack();

    public static void main(String[] args) {

        boolean running = true;

        printSystemHeader();

        while (running) {

            displayMainMenu();

            int choice = readInteger(
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    patientManagementMenu();
                    break;

                case 2:
                    emergencyQueueMenu();
                    break;

                case 3:
                    treatmentHistoryMenu();
                    break;

                case 4:
                    patientVisitHistoryMenu();
                    break;

                case 5:
                    displaySystemSummary();
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid menu option. "
                                    + "Please select a number from 0 to 5."
                    );
            }
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println(" Thank you for using the system.");
        System.out.println(" Program closed successfully.");
        System.out.println("========================================");

        scanner.close();
    }

    /*
     * Displays the system title when the program starts.
     */
    private static void printSystemHeader() {

        System.out.println();
        System.out.println("========================================");
        System.out.println(" MINI HOSPITAL EMERGENCY");
        System.out.println(" MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println(
                "Data Structures and Algorithms Assignment"
        );
        System.out.println("========================================");
    }

    /*
     * Displays the main menu.
     */
    private static void displayMainMenu() {

        System.out.println();
        System.out.println("============== MAIN MENU ===============");
        System.out.println("1. Patient Record Management");
        System.out.println("2. Emergency Queue Management");
        System.out.println("3. Treatment History Management");
        System.out.println("4. Patient Visit History Management");
        System.out.println("5. Display System Summary");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    // =====================================================
    // PATIENT RECORD MANAGEMENT
    // =====================================================

    private static void patientManagementMenu() {

        boolean backToMainMenu = false;

        while (!backToMainMenu) {

            System.out.println();
            System.out.println("====== PATIENT RECORD MANAGEMENT =======");
            System.out.println("1. Register New Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. Display All Patients");
            System.out.println("5. Display Patient BST Structure");
            System.out.println("0. Back to Main Menu");
            System.out.println("========================================");

            int choice = readInteger(
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    deletePatient();
                    break;

                case 4:
                    patientBST.displayInOrder();
                    break;

                case 5:
                    patientBST.displayTreeStructure();
                    break;

                case 0:
                    backToMainMenu = true;
                    break;

                default:
                    System.out.println(
                            "Invalid option. "
                                    + "Please select a number from 0 to 5."
                    );
            }
        }
    }

    /*
     * Registers a new patient in the BST.
     */
    private static void registerPatient() {

        System.out.println();
        System.out.println("----------- REGISTER PATIENT -----------");

        int patientId = readPositiveInteger(
                "Enter Patient ID: "
        );

        /*
         * Check whether the Patient ID already exists.
         */
        if (patientBST.search(patientId) != null) {

            System.out.println(
                    "Patient ID "
                            + patientId
                            + " already exists."
            );

            System.out.println(
                    "Patient registration was cancelled."
            );

            return;
        }

        String patientName = readNonEmptyString(
                "Enter Patient Name: "
        );

        int age = readAge(
                "Enter Patient Age: "
        );

        String contactNumber = readContactNumber(
                "Enter Contact Number: "
        );

        String medicalCondition = readNonEmptyString(
                "Enter Medical Condition: "
        );

        Patient patient = new Patient(
                patientId,
                patientName,
                age,
                contactNumber,
                medicalCondition
        );

        boolean inserted = patientBST.insert(patient);

        if (inserted) {

            System.out.println();
            System.out.println(
                    "Patient registered successfully."
            );

            patient.displayPatient();

        } else {

            System.out.println(
                    "Patient registration failed."
            );
        }
    }

    /*
     * Searches for a patient using Patient ID.
     */
    private static void searchPatient() {

        System.out.println();
        System.out.println("------------- SEARCH PATIENT -----------");

        int patientId = readPositiveInteger(
                "Enter Patient ID to search: "
        );

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println(
                    "No patient found with ID "
                            + patientId
                            + "."
            );

        } else {

            System.out.println(
                    "Patient found successfully:"
            );

            patient.displayPatient();
        }
    }

    /*
     * Deletes a patient from the BST.
     */
    private static void deletePatient() {

        System.out.println();
        System.out.println("------------- DELETE PATIENT -----------");

        int patientId = readPositiveInteger(
                "Enter Patient ID to delete: "
        );

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println(
                    "No patient found with ID "
                            + patientId
                            + "."
            );

            return;
        }

        /*
         * Prevent deleting a patient who is currently
         * waiting in the emergency queue.
         */
        if (emergencyQueue.contains(patientId)) {

            System.out.println(
                    "Patient ID "
                            + patientId
                            + " is currently waiting in "
                            + "the emergency queue."
            );

            System.out.println(
                    "Remove or treat the patient before "
                            + "deleting the patient record."
            );

            return;
        }

        System.out.println("Patient selected for deletion:");
        patient.displayPatient();

        boolean confirmed = readConfirmation(
                "Are you sure you want to delete this patient? (Y/N): "
        );

        if (!confirmed) {

            System.out.println(
                    "Patient deletion was cancelled."
            );

            return;
        }

        boolean deleted = patientBST.delete(patientId);

        if (deleted) {

            System.out.println(
                    "Patient ID "
                            + patientId
                            + " deleted successfully."
            );

        } else {

            System.out.println(
                    "Patient deletion failed."
            );
        }
    }

    // =====================================================
    // EMERGENCY QUEUE MANAGEMENT
    // =====================================================

    private static void emergencyQueueMenu() {

        boolean backToMainMenu = false;

        while (!backToMainMenu) {

            System.out.println();
            System.out.println("====== EMERGENCY QUEUE MANAGEMENT ======");
            System.out.println("1. Add Patient to Emergency Queue");
            System.out.println("2. View Next Patient");
            System.out.println("3. Send Next Patient for Treatment");
            System.out.println("4. Display All Waiting Patients");
            System.out.println("0. Back to Main Menu");
            System.out.println("========================================");

            int choice = readInteger(
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    addPatientToEmergencyQueue();
                    break;

                case 2:
                    viewNextEmergencyPatient();
                    break;

                case 3:
                    dequeuePatientOnly();
                    break;

                case 4:
                    emergencyQueue.displayQueue();
                    break;

                case 0:
                    backToMainMenu = true;
                    break;

                default:
                    System.out.println(
                            "Invalid option. "
                                    + "Please select a number from 0 to 4."
                    );
            }
        }
    }

    /*
     * Searches for a registered patient and adds
     * that patient to the queue.
     */
    private static void addPatientToEmergencyQueue() {

        System.out.println();
        System.out.println("-------- ADD TO EMERGENCY QUEUE --------");

        if (patientBST.isEmpty()) {

            System.out.println(
                    "No patients are registered."
            );

            System.out.println(
                    "Please register the patient first."
            );

            return;
        }

        int patientId = readPositiveInteger(
                "Enter registered Patient ID: "
        );

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println(
                    "Patient ID "
                            + patientId
                            + " is not registered."
            );

            System.out.println(
                    "Please register the patient first."
            );

            return;
        }

        if (emergencyQueue.contains(patientId)) {

            System.out.println(
                    "Patient ID "
                            + patientId
                            + " is already waiting in "
                            + "the emergency queue."
            );

            return;
        }

        System.out.println("Registered patient found:");
        patient.displayPatient();

        boolean added = emergencyQueue.enqueue(patient);

        if (added) {

            System.out.println(
                    "Patient "
                            + patient.getPatientId()
                            + " - "
                            + patient.getPatientName()
                            + " added to the emergency queue."
            );

            System.out.println(
                    "Current queue position: "
                            + emergencyQueue.getSize()
            );
        }
    }

    /*
     * Shows the next patient without removing
     * the patient from the queue.
     */
    private static void viewNextEmergencyPatient() {

        System.out.println();
        System.out.println("---------- NEXT QUEUE PATIENT ----------");

        Patient patient = emergencyQueue.peek();

        if (patient != null) {

            System.out.println(
                    "Next patient waiting for treatment:"
            );

            patient.displayPatient();
        }
    }

    /*
     * Removes the first patient from the queue.
     *
     * This option only removes the patient.
     * Completed treatment entry is handled through
     * the Treatment History menu.
     */
    private static void dequeuePatientOnly() {

        System.out.println();
        System.out.println("------- SEND PATIENT FOR TREATMENT ------");

        Patient patient = emergencyQueue.peek();

        if (patient == null) {
            return;
        }

        System.out.println("Next patient:");
        patient.displayPatient();

        boolean confirmed = readConfirmation(
                "Send this patient for treatment? (Y/N): "
        );

        if (!confirmed) {

            System.out.println(
                    "Operation cancelled. "
                            + "The patient remains in the queue."
            );

            return;
        }

        Patient removedPatient = emergencyQueue.dequeue();

        if (removedPatient != null) {

            System.out.println(
                    "Patient "
                            + removedPatient.getPatientId()
                            + " - "
                            + removedPatient.getPatientName()
                            + " removed from the queue."
            );

            System.out.println(
                    "The patient has been sent for treatment."
            );
        }
    }

    // =====================================================
    // TREATMENT HISTORY MANAGEMENT
    // =====================================================

    private static void treatmentHistoryMenu() {

        boolean backToMainMenu = false;

        while (!backToMainMenu) {

            System.out.println();
            System.out.println("===== TREATMENT HISTORY MANAGEMENT =====");
            System.out.println("1. Complete Next Patient Treatment");
            System.out.println("2. Add Treatment for Registered Patient");
            System.out.println("3. View Latest Treatment Record");
            System.out.println("4. Search Treatment Record");
            System.out.println("5. Remove Latest Treatment Record");
            System.out.println("6. Display All Treatment Records");
            System.out.println("0. Back to Main Menu");
            System.out.println("========================================");

            int choice = readInteger(
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    completeNextPatientTreatment();
                    break;

                case 2:
                    addTreatmentForRegisteredPatient();
                    break;

                case 3:
                    viewLatestTreatment();
                    break;

                case 4:
                    searchTreatmentRecord();
                    break;

                case 5:
                    removeLatestTreatmentRecord();
                    break;

                case 6:
                    treatmentStack.displayStack();
                    break;

                case 0:
                    backToMainMenu = true;
                    break;

                default:
                    System.out.println(
                            "Invalid option. "
                                    + "Please select a number from 0 to 6."
                    );
            }
        }
    }

    /*
     * Full integrated hospital flow:
     *
     * 1. Get the first patient from the queue.
     * 2. Enter treatment information.
     * 3. Create a TreatmentRecord.
     * 4. Push it into the treatment stack.
     * 5. Create a Visit.
     * 6. Add it to the patient's visit history.
     * 7. Remove the patient from the queue.
     */
    private static void completeNextPatientTreatment() {

        System.out.println();
        System.out.println("------- COMPLETE NEXT TREATMENT --------");

        Patient patient = emergencyQueue.peek();

        if (patient == null) {

            System.out.println(
                    "A treatment cannot be completed because "
                            + "the emergency queue is empty."
            );

            return;
        }

        System.out.println("Next patient for treatment:");
        patient.displayPatient();

        String treatmentId = readUniqueTreatmentId();

        String treatmentDate = readNonEmptyString(
                "Enter Treatment Date (DD/MM/YYYY): "
        );

        String doctorName = readNonEmptyString(
                "Enter Doctor Name: "
        );

        String diagnosis = readNonEmptyString(
                "Enter Diagnosis: "
        );

        String treatmentProvided = readNonEmptyString(
                "Enter Treatment Provided: "
        );

        TreatmentRecord treatmentRecord =
                new TreatmentRecord(
                        treatmentId,
                        patient.getPatientId(),
                        patient.getPatientName(),
                        treatmentDate,
                        doctorName,
                        diagnosis,
                        treatmentProvided
                );

        /*
         * Use a related Visit ID.
         *
         * Example:
         * Treatment ID T001 becomes Visit ID V-T001.
         */
        String visitId = generateVisitId(treatmentId);

        Visit visit = new Visit(
                visitId,
                treatmentDate,
                doctorName,
                diagnosis,
                treatmentProvided
        );

        boolean treatmentAdded =
                treatmentStack.push(treatmentRecord);

        if (!treatmentAdded) {

            System.out.println(
                    "Treatment completion failed."
            );

            return;
        }

        boolean visitAdded =
                patient.getVisitHistory().addVisit(visit);

        /*
         * The patient is removed only after the
         * treatment record is saved successfully.
         */
        Patient removedPatient = emergencyQueue.dequeue();

        System.out.println();
        System.out.println("Treatment completed successfully.");

        System.out.println(
                "Treatment "
                        + treatmentId
                        + " was pushed to the treatment stack."
        );

        if (visitAdded) {

            System.out.println(
                    "Visit "
                            + visitId
                            + " was added to Patient "
                            + patient.getPatientId()
                            + "'s visit history."
            );
        }

        if (removedPatient != null) {

            System.out.println(
                    "Patient "
                            + removedPatient.getPatientId()
                            + " was removed from the emergency queue."
            );
        }

        System.out.println();
        treatmentRecord.displayTreatmentRecord();
    }

    /*
     * Allows treatment to be recorded for a registered
     * patient who is not in the emergency queue.
     */
    private static void addTreatmentForRegisteredPatient() {

        System.out.println();
        System.out.println("------ ADD REGISTERED PATIENT TREATMENT ------");

        if (patientBST.isEmpty()) {

            System.out.println(
                    "No registered patients are available."
            );

            return;
        }

        int patientId = readPositiveInteger(
                "Enter Patient ID: "
        );

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println(
                    "No patient found with ID "
                            + patientId
                            + "."
            );

            return;
        }

        String treatmentId = readUniqueTreatmentId();

        String treatmentDate = readNonEmptyString(
                "Enter Treatment Date (DD/MM/YYYY): "
        );

        String doctorName = readNonEmptyString(
                "Enter Doctor Name: "
        );

        String diagnosis = readNonEmptyString(
                "Enter Diagnosis: "
        );

        String treatmentProvided = readNonEmptyString(
                "Enter Treatment Provided: "
        );

        TreatmentRecord treatmentRecord =
                new TreatmentRecord(
                        treatmentId,
                        patient.getPatientId(),
                        patient.getPatientName(),
                        treatmentDate,
                        doctorName,
                        diagnosis,
                        treatmentProvided
                );

        String visitId = generateVisitId(treatmentId);

        Visit visit = new Visit(
                visitId,
                treatmentDate,
                doctorName,
                diagnosis,
                treatmentProvided
        );

        boolean treatmentAdded =
                treatmentStack.push(treatmentRecord);

        if (!treatmentAdded) {
            return;
        }

        boolean visitAdded =
                patient.getVisitHistory().addVisit(visit);

        System.out.println(
                "Treatment record added successfully."
        );

        if (visitAdded) {

            System.out.println(
                    "The treatment was also added to "
                            + "the patient's visit history."
            );
        }
    }

    /*
     * Generates a visit ID from a Treatment ID.
     */
    private static String generateVisitId(
            String treatmentId) {

        String basicVisitId =
                "V-" + treatmentId.trim().toUpperCase();

        String visitId = basicVisitId;
        int number = 1;

        /*
         * This extra check handles a rare situation
         * where the generated Visit ID already exists.
         */
        while (visitIdExistsInAnyPatientNotAvailable(
                visitId)) {

            visitId = basicVisitId + "-" + number;
            number++;
        }

        return visitId;
    }

    /*
     * Visit IDs only need to be unique inside one patient's
     * history. Therefore, full-tree validation is unnecessary.
     *
     * This method is kept simple for future extension.
     */
    private static boolean visitIdExistsInAnyPatientNotAvailable(
            String visitId) {

        return false;
    }

    /*
     * Displays the latest treatment without removing it.
     */
    private static void viewLatestTreatment() {

        System.out.println();
        System.out.println("--------- LATEST TREATMENT RECORD -------");

        TreatmentRecord treatmentRecord =
                treatmentStack.peek();

        if (treatmentRecord != null) {

            treatmentRecord.displayTreatmentRecord();
        }
    }

    /*
     * Searches for a treatment using Treatment ID.
     */
    private static void searchTreatmentRecord() {

        System.out.println();
        System.out.println("--------- SEARCH TREATMENT RECORD -------");

        String treatmentId = readNonEmptyString(
                "Enter Treatment ID: "
        );

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

    /*
     * Pops the latest record from the stack.
     */
    private static void removeLatestTreatmentRecord() {

        System.out.println();
        System.out.println("--------- REMOVE LATEST TREATMENT -------");

        TreatmentRecord latestRecord =
                treatmentStack.peek();

        if (latestRecord == null) {
            return;
        }

        System.out.println(
                "Latest treatment selected:"
        );

        latestRecord.displayTreatmentRecord();

        boolean confirmed = readConfirmation(
                "Remove this treatment record? (Y/N): "
        );

        if (!confirmed) {

            System.out.println(
                    "Treatment removal was cancelled."
            );

            return;
        }

        TreatmentRecord removedRecord =
                treatmentStack.pop();

        if (removedRecord != null) {

            System.out.println(
                    "Treatment "
                            + removedRecord.getTreatmentId()
                            + " removed from the stack."
            );

            System.out.println(
                    "Note: The patient's visit history "
                            + "was not removed."
            );
        }
    }

    /*
     * Reads a non-duplicate Treatment ID.
     */
    private static String readUniqueTreatmentId() {

        while (true) {

            String treatmentId = readNonEmptyString(
                    "Enter Treatment ID: "
            ).toUpperCase();

            if (!treatmentStack.contains(treatmentId)) {
                return treatmentId;
            }

            System.out.println(
                    "Treatment ID "
                            + treatmentId
                            + " already exists."
            );

            System.out.println(
                    "Please enter a different Treatment ID."
            );
        }
    }

    // =====================================================
    // PATIENT VISIT HISTORY MANAGEMENT
    // =====================================================

    private static void patientVisitHistoryMenu() {

        boolean backToMainMenu = false;

        while (!backToMainMenu) {

            System.out.println();
            System.out.println("===== PATIENT VISIT HISTORY MENU =======");
            System.out.println("1. Add New Visit");
            System.out.println("2. Search Visit");
            System.out.println("3. Remove Visit");
            System.out.println("4. Display Patient Visit History");
            System.out.println("0. Back to Main Menu");
            System.out.println("========================================");

            int choice = readInteger(
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    addNewVisit();
                    break;

                case 2:
                    searchPatientVisit();
                    break;

                case 3:
                    removePatientVisit();
                    break;

                case 4:
                    displayPatientVisitHistory();
                    break;

                case 0:
                    backToMainMenu = true;
                    break;

                default:
                    System.out.println(
                            "Invalid option. "
                                    + "Please select a number from 0 to 4."
                    );
            }
        }
    }

    /*
     * Adds a visit manually to a registered patient.
     */
    private static void addNewVisit() {

        System.out.println();
        System.out.println("-------------- ADD NEW VISIT -----------");

        Patient patient = findRegisteredPatient();

        if (patient == null) {
            return;
        }

        String visitId = readNonEmptyString(
                "Enter Visit ID: "
        ).toUpperCase();

        if (patient.getVisitHistory().contains(visitId)) {

            System.out.println(
                    "Visit ID "
                            + visitId
                            + " already exists for this patient."
            );

            return;
        }

        String visitDate = readNonEmptyString(
                "Enter Visit Date (DD/MM/YYYY): "
        );

        String doctorName = readNonEmptyString(
                "Enter Doctor Name: "
        );

        String diagnosis = readNonEmptyString(
                "Enter Diagnosis: "
        );

        String treatment = readNonEmptyString(
                "Enter Treatment: "
        );

        Visit visit = new Visit(
                visitId,
                visitDate,
                doctorName,
                diagnosis,
                treatment
        );

        boolean added =
                patient.getVisitHistory().addVisit(visit);

        if (added) {

            System.out.println(
                    "Visit "
                            + visitId
                            + " added successfully to Patient "
                            + patient.getPatientId()
                            + " - "
                            + patient.getPatientName()
                            + "."
            );
        }
    }

    /*
     * Searches for a visit in a patient's history.
     */
    private static void searchPatientVisit() {

        System.out.println();
        System.out.println("------------- SEARCH VISIT -------------");

        Patient patient = findRegisteredPatient();

        if (patient == null) {
            return;
        }

        String visitId = readNonEmptyString(
                "Enter Visit ID to search: "
        );

        Visit visit =
                patient.getVisitHistory()
                        .searchVisit(visitId);

        if (visit == null) {

            System.out.println(
                    "No visit found with ID "
                            + visitId
                            + " for Patient "
                            + patient.getPatientId()
                            + "."
            );

        } else {

            System.out.println(
                    "Visit found successfully:"
            );

            visit.displayVisit();
        }
    }

    /*
     * Removes a visit from a patient's linked list.
     */
    private static void removePatientVisit() {

        System.out.println();
        System.out.println("------------- REMOVE VISIT -------------");

        Patient patient = findRegisteredPatient();

        if (patient == null) {
            return;
        }

        if (patient.getVisitHistory().isEmpty()) {

            System.out.println(
                    "This patient has no visit history."
            );

            return;
        }

        String visitId = readNonEmptyString(
                "Enter Visit ID to remove: "
        );

        Visit visit =
                patient.getVisitHistory()
                        .searchVisit(visitId);

        if (visit == null) {

            System.out.println(
                    "No visit found with ID "
                            + visitId
                            + "."
            );

            return;
        }

        System.out.println("Visit selected for removal:");
        visit.displayVisit();

        boolean confirmed = readConfirmation(
                "Remove this visit? (Y/N): "
        );

        if (!confirmed) {

            System.out.println(
                    "Visit removal was cancelled."
            );

            return;
        }

        boolean removed =
                patient.getVisitHistory()
                        .removeVisit(visitId);

        if (removed) {

            System.out.println(
                    "Visit "
                            + visitId
                            + " removed successfully."
            );
        }
    }

    /*
     * Displays one patient's visit history.
     */
    private static void displayPatientVisitHistory() {

        System.out.println();
        System.out.println("---------- DISPLAY VISIT HISTORY -------");

        Patient patient = findRegisteredPatient();

        if (patient == null) {
            return;
        }

        System.out.println(
                "Patient: "
                        + patient.getPatientId()
                        + " - "
                        + patient.getPatientName()
        );

        patient.getVisitHistory().displayVisits();
    }

    /*
     * Searches and returns a patient for visit operations.
     */
    private static Patient findRegisteredPatient() {

        if (patientBST.isEmpty()) {

            System.out.println(
                    "No patients are registered."
            );

            return null;
        }

        int patientId = readPositiveInteger(
                "Enter Patient ID: "
        );

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println(
                    "No patient found with ID "
                            + patientId
                            + "."
            );
        }

        return patient;
    }

    // =====================================================
    // SYSTEM SUMMARY
    // =====================================================

    private static void displaySystemSummary() {

        System.out.println();
        System.out.println("=========== SYSTEM SUMMARY =============");

        if (patientBST.isEmpty()) {

            System.out.println(
                    "Registered Patient Records: Empty"
            );

        } else {

            System.out.println(
                    "Registered Patient Records: Available"
            );
        }

        System.out.println(
                "Patients Waiting in Queue: "
                        + emergencyQueue.getSize()
        );

        System.out.println(
                "Completed Treatment Records: "
                        + treatmentStack.getSize()
        );

        System.out.println("----------------------------------------");
        System.out.println("Patient Records: Binary Search Tree");
        System.out.println("Emergency Patients: Queue, FIFO");
        System.out.println("Treatment History: Stack, LIFO");
        System.out.println(
                "Visit History: Singly Linked List"
        );
        System.out.println("========================================");
    }

    // =====================================================
    // INPUT VALIDATION METHODS
    // =====================================================

    /*
     * Reads any valid integer.
     */
    private static int readInteger(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException exception) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    /*
     * Reads an integer greater than zero.
     */
    private static int readPositiveInteger(
            String message) {

        while (true) {

            int number = readInteger(message);

            if (number > 0) {
                return number;
            }

            System.out.println(
                    "The value must be greater than zero."
            );
        }
    }

    /*
     * Reads and validates patient age.
     */
    private static int readAge(String message) {

        while (true) {

            int age = readInteger(message);

            if (age >= 1 && age <= 120) {
                return age;
            }

            System.out.println(
                    "Age must be between 1 and 120."
            );
        }
    }

    /*
     * Prevents empty string input.
     */
    private static String readNonEmptyString(
            String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "This value cannot be empty."
            );
        }
    }

    /*
     * Validates a contact number.
     *
     * The contact number must contain only digits
     * and must contain between 7 and 15 digits.
     */
    private static String readContactNumber(
            String message) {

        while (true) {

            System.out.print(message);
            String contactNumber =
                    scanner.nextLine().trim();

            if (contactNumber.matches("\\d{7,15}")) {
                return contactNumber;
            }

            System.out.println(
                    "Invalid contact number."
            );

            System.out.println(
                    "Enter only 7 to 15 numeric digits."
            );
        }
    }

    /*
     * Reads a Yes or No confirmation.
     */
    private static boolean readConfirmation(
            String message) {

        while (true) {

            System.out.print(message);
            String answer =
                    scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("Y")
                    || answer.equalsIgnoreCase("YES")) {

                return true;
            }

            if (answer.equalsIgnoreCase("N")
                    || answer.equalsIgnoreCase("NO")) {

                return false;
            }

            System.out.println(
                    "Invalid answer. Please enter Y or N."
            );
        }
    }
}