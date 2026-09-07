# Mini Hospital Emergency Management System

## Student Information

- **Student Name:** M.I.M. Amhar
- **Student ID:** 23DA2-0515
- **Module:** CIT300 - Data Structures and Algorithms
- **Assignment:** Individual Mid Assignment
- **Project Title:** Mini Hospital Emergency Management System Using Data Structures

---

## Project Description

The Mini Hospital Emergency Management System is a Java console application developed to demonstrate the practical implementation of fundamental data structures.

The system manages patient registration, emergency patient queues, completed treatment records, and individual patient visit histories.

The following data structures are manually implemented in this project:

1. Binary Search Tree
2. Queue
3. Stack
4. Singly Linked List

Java's built-in collection classes are not used for the main data structure implementations.

---

## Main Features

### Patient Record Management

- Register a new patient
- Search for a patient using Patient ID
- Delete a patient
- Display patients in ascending order of Patient ID
- Display the Binary Search Tree structure
- Prevent duplicate Patient IDs

### Emergency Queue Management

- Add a registered patient to the emergency queue
- View the next patient in the queue
- Remove the next patient for treatment
- Display all waiting patients
- Prevent duplicate queue entries
- Handle an empty queue safely

### Treatment History Management

- Complete the treatment of the next emergency patient
- Add a treatment for a registered patient
- Store completed treatments in a stack
- View the latest treatment record
- Search for a treatment record
- Remove the latest treatment record
- Display all completed treatments
- Prevent duplicate Treatment IDs
- Handle an empty stack safely

### Patient Visit History Management

- Maintain an individual visit history for every patient
- Add a new visit
- Search for a visit using Visit ID
- Remove a visit
- Display a patient's visit history
- Prevent duplicate Visit IDs
- Handle an empty visit history safely

### Input Validation

- Prevent text input where a number is required
- Prevent zero and negative Patient IDs
- Validate patient age
- Prevent empty names and text fields
- Validate contact numbers
- Prevent invalid menu options
- Request confirmation before deletion operations

---

## Data Structures Used

### 1. Binary Search Tree

The Binary Search Tree is used to store patient records.

The Patient ID is used as the key.

- A smaller Patient ID is inserted into the left subtree.
- A larger Patient ID is inserted into the right subtree.
- In-order traversal displays patients in ascending Patient ID order.
- Patient deletion handles nodes with zero, one, or two children.

Main implementation files:

```text
EmergencyQueue.java
Main.java
Patient.java
PatientBST.java
PatientBSTNode.java
QueueNode.java
StackNode.java
TreatmentRecord.java
TreatmentStack.java
Visit.java
VisitHistory.java
VisitNode.java
```

## Auther
M.I.M. Amhar

## GitHub Repository

Repository Link:

https://github.com/mimohammedamhar/MiniHospitalEmergencySystem.git