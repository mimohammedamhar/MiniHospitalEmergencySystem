public class PatientBST {

    private PatientBSTNode root;

    public PatientBST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /*
     * Inserts a new patient into the Binary Search Tree.
     *
     * Returns true if the patient was inserted.
     * Returns false if the Patient ID already exists.
     */
    public boolean insert(Patient patient) {

        if (patient == null) {
            System.out.println("Cannot insert an empty patient record.");
            return false;
        }

        if (root == null) {
            root = new PatientBSTNode(patient);
            return true;
        }

        return insertRecursive(root, patient);
    }

    private boolean insertRecursive(
            PatientBSTNode currentNode,
            Patient patient) {

        int newPatientId = patient.getPatientId();
        int currentPatientId = currentNode.patient.getPatientId();

        if (newPatientId < currentPatientId) {

            if (currentNode.left == null) {
                currentNode.left = new PatientBSTNode(patient);
                return true;
            }

            return insertRecursive(currentNode.left, patient);

        } else if (newPatientId > currentPatientId) {

            if (currentNode.right == null) {
                currentNode.right = new PatientBSTNode(patient);
                return true;
            }

            return insertRecursive(currentNode.right, patient);

        } else {
            // Duplicate Patient ID
            return false;
        }
    }

    /*
     * Searches for a patient using Patient ID.
     *
     * Returns the Patient object when found.
     * Returns null when the patient does not exist.
     */
    public Patient search(int patientId) {
        return searchRecursive(root, patientId);
    }

    private Patient searchRecursive(
            PatientBSTNode currentNode,
            int patientId) {

        if (currentNode == null) {
            return null;
        }

        int currentPatientId = currentNode.patient.getPatientId();

        if (patientId == currentPatientId) {
            return currentNode.patient;
        }

        if (patientId < currentPatientId) {
            return searchRecursive(currentNode.left, patientId);
        }

        return searchRecursive(currentNode.right, patientId);
    }

    /*
     * Displays all patients in ascending order of Patient ID.
     */
    public void displayInOrder() {

        if (isEmpty()) {
            System.out.println("No patient records are available.");
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println(" PATIENTS IN ASCENDING ORDER OF ID");
        System.out.println("========================================");

        inOrderRecursive(root);

        System.out.println("========================================");
    }

    private void inOrderRecursive(PatientBSTNode currentNode) {

        if (currentNode == null) {
            return;
        }

        // Visit left subtree.
        inOrderRecursive(currentNode.left);

        // Display current patient.
        System.out.println(currentNode.patient);

        // Visit right subtree.
        inOrderRecursive(currentNode.right);
    }

    /*
     * Deletes a patient using Patient ID.
     *
     * Returns true if the patient was deleted.
     * Returns false if the patient was not found.
     */
    public boolean delete(int patientId) {

        if (search(patientId) == null) {
            return false;
        }

        root = deleteRecursive(root, patientId);
        return true;
    }

    private PatientBSTNode deleteRecursive(
            PatientBSTNode currentNode,
            int patientId) {

        if (currentNode == null) {
            return null;
        }

        int currentPatientId = currentNode.patient.getPatientId();

        if (patientId < currentPatientId) {

            currentNode.left =
                    deleteRecursive(currentNode.left, patientId);

        } else if (patientId > currentPatientId) {

            currentNode.right =
                    deleteRecursive(currentNode.right, patientId);

        } else {

            /*
             * Case 1:
             * The node has no children.
             */
            if (currentNode.left == null
                    && currentNode.right == null) {

                return null;
            }

            /*
             * Case 2:
             * The node has only a right child.
             */
            if (currentNode.left == null) {
                return currentNode.right;
            }

            /*
             * Case 2:
             * The node has only a left child.
             */
            if (currentNode.right == null) {
                return currentNode.left;
            }

            /*
             * Case 3:
             * The node has two children.
             *
             * Find the smallest node in the right subtree.
             * This node is called the inorder successor.
             */
            PatientBSTNode successor =
                    findMinimumNode(currentNode.right);

            // Replace the current patient's data.
            currentNode.patient = successor.patient;

            // Delete the duplicate successor node.
            currentNode.right =
                    deleteRecursive(
                            currentNode.right,
                            successor.patient.getPatientId()
                    );
        }

        return currentNode;
    }

    /*
     * Finds the node with the smallest Patient ID
     * in a given subtree.
     */
    private PatientBSTNode findMinimumNode(
            PatientBSTNode currentNode) {

        PatientBSTNode minimumNode = currentNode;

        while (minimumNode.left != null) {
            minimumNode = minimumNode.left;
        }

        return minimumNode;
    }

    /*
     * Displays the tree structure sideways.
     *
     * This method is optional, but it is useful
     * when testing and demonstrating the BST.
     */
    public void displayTreeStructure() {

        if (isEmpty()) {
            System.out.println("The patient BST is empty.");
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println(" PATIENT BST STRUCTURE");
        System.out.println("========================================");

        displayTreeRecursive(root, 0);

        System.out.println("========================================");
    }

    private void displayTreeRecursive(
            PatientBSTNode currentNode,
            int level) {

        if (currentNode == null) {
            return;
        }

        // Display the right subtree first.
        displayTreeRecursive(currentNode.right, level + 1);

        // Add indentation according to the tree level.
        for (int index = 0; index < level; index++) {
            System.out.print("        ");
        }

        System.out.println(
                currentNode.patient.getPatientId()
                        + " - "
                        + currentNode.patient.getPatientName()
        );

        // Display the left subtree.
        displayTreeRecursive(currentNode.left, level + 1);
    }
}