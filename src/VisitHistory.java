public class VisitHistory {

    private VisitNode head;
    private VisitNode tail;
    private int size;

    public VisitHistory() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
     * Checks whether the visit history is empty.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /*
     * Returns the number of visits in this history.
     */
    public int getSize() {
        return size;
    }

    /*
     * Adds a new visit to the end of the linked list.
     *
     * Returns true when the visit is added successfully.
     * Returns false for a null visit or duplicate Visit ID.
     */
    public boolean addVisit(Visit visit) {

        if (visit == null) {
            System.out.println(
                    "Cannot add an empty visit record."
            );
            return false;
        }

        if (visit.getVisitId() == null
                || visit.getVisitId().trim().isEmpty()) {

            System.out.println(
                    "Visit ID cannot be empty."
            );
            return false;
        }

        /*
         * Prevent duplicate Visit IDs within
         * the same patient's visit history.
         */
        if (contains(visit.getVisitId())) {
            System.out.println(
                    "Visit ID "
                            + visit.getVisitId()
                            + " already exists in this patient's history."
            );
            return false;
        }

        VisitNode newNode = new VisitNode(visit);

        /*
         * If the linked list is empty, the new node
         * becomes both head and tail.
         */
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {

            /*
             * Add the new node after the current tail.
             */
            tail.next = newNode;

            /*
             * Move tail to the newly added node.
             */
            tail = newNode;
        }

        size++;
        return true;
    }

    /*
     * Searches for a visit using Visit ID.
     *
     * Returns the Visit object when found.
     * Returns null when the Visit ID does not exist.
     */
    public Visit searchVisit(String visitId) {

        if (visitId == null || visitId.trim().isEmpty()) {
            return null;
        }

        VisitNode currentNode = head;

        while (currentNode != null) {

            if (currentNode.visit
                    .getVisitId()
                    .equalsIgnoreCase(visitId.trim())) {

                return currentNode.visit;
            }

            currentNode = currentNode.next;
        }

        return null;
    }

    /*
     * Checks whether a Visit ID already exists.
     */
    public boolean contains(String visitId) {
        return searchVisit(visitId) != null;
    }

    /*
     * Removes a visit using Visit ID.
     *
     * Returns true when the visit is removed.
     * Returns false when the Visit ID is not found.
     */
    public boolean removeVisit(String visitId) {

        if (isEmpty()) {
            System.out.println(
                    "Visit history is empty. "
                            + "No visit can be removed."
            );
            return false;
        }

        if (visitId == null || visitId.trim().isEmpty()) {
            System.out.println(
                    "Visit ID cannot be empty."
            );
            return false;
        }

        /*
         * Case 1:
         * The visit to be removed is the head node.
         */
        if (head.visit
                .getVisitId()
                .equalsIgnoreCase(visitId.trim())) {

            head = head.next;
            size--;

            /*
             * If the list becomes empty,
             * tail must also become null.
             */
            if (head == null) {
                tail = null;
            }

            return true;
        }

        /*
         * Start from the second node.
         * previousNode stays one node behind currentNode.
         */
        VisitNode previousNode = head;
        VisitNode currentNode = head.next;

        while (currentNode != null) {

            if (currentNode.visit
                    .getVisitId()
                    .equalsIgnoreCase(visitId.trim())) {

                /*
                 * Skip the current node.
                 */
                previousNode.next = currentNode.next;

                /*
                 * If the removed node is the tail,
                 * update tail to the previous node.
                 */
                if (currentNode == tail) {
                    tail = previousNode;
                }

                size--;
                return true;
            }

            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        return false;
    }

    /*
     * Displays all previous hospital visits
     * from the oldest visit to the latest visit.
     */
    public void displayVisits() {

        if (isEmpty()) {
            System.out.println(
                    "No visit history is available for this patient."
            );
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println(" PATIENT VISIT HISTORY");
        System.out.println("========================================");
        System.out.println("Number of visits: " + size);
        System.out.println("----------------------------------------");

        VisitNode currentNode = head;
        int position = 1;

        while (currentNode != null) {

            Visit visit = currentNode.visit;

            System.out.println("Visit Number: " + position);
            System.out.println(
                    "Visit ID    : " + visit.getVisitId()
            );
            System.out.println(
                    "Visit Date  : " + visit.getVisitDate()
            );
            System.out.println(
                    "Doctor Name : " + visit.getDoctorName()
            );
            System.out.println(
                    "Diagnosis   : " + visit.getDiagnosis()
            );
            System.out.println(
                    "Treatment   : " + visit.getTreatment()
            );

            if (currentNode == head && currentNode == tail) {
                System.out.println(
                        "List Status : HEAD and TAIL"
                );
            } else if (currentNode == head) {
                System.out.println(
                        "List Status : HEAD - Oldest Visit"
                );
            } else if (currentNode == tail) {
                System.out.println(
                        "List Status : TAIL - Latest Visit"
                );
            } else {
                System.out.println(
                        "List Status : Previous Visit"
                );
            }

            System.out.println("----------------------------------------");

            currentNode = currentNode.next;
            position++;
        }

        System.out.println(
                "Data Structure: Singly Linked List"
        );
        System.out.println("========================================");
    }
}