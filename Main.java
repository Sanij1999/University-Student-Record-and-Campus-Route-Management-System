import java.util.Scanner;

public class Main {
    static StudentLinkedList studentList = new StudentLinkedList();
    static ActionStack actionStack = new ActionStack();
    static ServiceQueue serviceQueue = new ServiceQueue();
    static StudentBST studentBST = new StudentBST();
    static StudentHashTable hashTable = new StudentHashTable();
    static CampusGraph campusGraph = new CampusGraph();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    studentList.displayAll();
                    break;
                case 5:
                    addServiceRequest();
                    break;
                case 6:
                    processServiceRequest();
                    break;
                case 7:
                    actionStack.displayActions();
                    break;
                case 8:
                    studentBST.displayInOrder();
                    break;
                case 9:
                    searchStudentHashing();
                    break;
                case 10:
                    addCampusLocation();
                    break;
                case 11:
                    removeCampusLocation();
                    break;
                case 12:
                    addCampusConnection();
                    break;
                case 13:
                    removeCampusConnection();
                    break;
                case 14:
                    campusGraph.displayConnections();
                    break;
                case 15:
                    traverseCampus();
                    break;
                case 16:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 16);
        sc.close();
    }

    static void printMenu() {
        System.out.println("\n===== University Student Record and Campus Route Management System =====");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0 || value > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    static void addStudent() {
        String id = readNonEmptyString("Enter Student ID: ");
        if (studentList.search(id) != null) {
            System.out.println("Error: Student ID already exists.");
            return;
        }
        String name = readNonEmptyString("Enter Student Name: ");
        String programme = readNonEmptyString("Enter Programme: ");
        double marks = readDouble("Enter Marks: ");
        Student student = new Student(id, name, programme, marks);
        studentList.add(student);
        studentBST.insert(student);
        hashTable.insert(student);
        actionStack.push("Added student: " + id);
        System.out.println("Student record added successfully.");
    }

    static void updateStudent() {
        String id = readNonEmptyString("Enter Student ID to update: ");
        Student existing = studentList.search(id);
        if (existing == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        String name = readNonEmptyString("Enter new Name: ");
        String programme = readNonEmptyString("Enter new Programme: ");
        double marks = readDouble("Enter new Marks: ");
        studentList.update(id, name, programme, marks);
        actionStack.push("Updated student: " + id);
        System.out.println("Student record updated successfully.");
    }

    static void deleteStudent() {
        String id = readNonEmptyString("Enter Student ID to delete: ");
        boolean removed = studentList.delete(id);
        if (!removed) {
            System.out.println("Error: Student not found.");
            return;
        }
        studentBST.delete(id);
        hashTable.delete(id);
        actionStack.push("Deleted student: " + id);
        System.out.println("Student record deleted successfully.");
    }

    static void addServiceRequest() {
        String id = readNonEmptyString("Enter Student ID for service request: ");
        if (studentList.search(id) == null) {
            System.out.println("Warning: Student ID does not exist in records, but request added anyway.");
        }
        serviceQueue.addRequest(id);
        actionStack.push("Service request added for: " + id);
        System.out.println("Service request added to queue.");
    }

    static void processServiceRequest() {
        String id = serviceQueue.processNext();
        if (id == null) {
            System.out.println("No service requests to process.");
            return;
        }
        actionStack.push("Processed service request for: " + id);
        System.out.println("Processed service request for Student ID: " + id);
    }

    static void searchStudentHashing() {
        String id = readNonEmptyString("Enter Student ID to search: ");
        Student student = hashTable.search(id);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found: " + student);
        }
    }

    static void addCampusLocation() {
        String location = readNonEmptyString("Enter Campus Location name: ");
        boolean added = campusGraph.addLocation(location);
        if (added) {
            actionStack.push("Added campus location: " + location);
            System.out.println("Campus location added successfully.");
        } else {
            System.out.println("Error: Location already exists.");
        }
    }

    static void removeCampusLocation() {
        String location = readNonEmptyString("Enter Campus Location name to remove: ");
        boolean removed = campusGraph.removeLocation(location);
        if (removed) {
            actionStack.push("Removed campus location: " + location);
            System.out.println("Campus location removed successfully.");
        } else {
            System.out.println("Error: Location not found.");
        }
    }

    static void addCampusConnection() {
        String loc1 = readNonEmptyString("Enter first location: ");
        String loc2 = readNonEmptyString("Enter second location: ");
        boolean added = campusGraph.addConnection(loc1, loc2);
        if (added) {
            actionStack.push("Added connection: " + loc1 + " - " + loc2);
            System.out.println("Connection added successfully.");
        } else {
            System.out.println("Error: Unable to add connection. Check that both locations exist and are not already connected.");
        }
    }

    static void removeCampusConnection() {
        String loc1 = readNonEmptyString("Enter first location: ");
        String loc2 = readNonEmptyString("Enter second location: ");
        boolean removed = campusGraph.removeConnection(loc1, loc2);
        if (removed) {
            actionStack.push("Removed connection: " + loc1 + " - " + loc2);
            System.out.println("Connection removed successfully.");
        } else {
            System.out.println("Error: Unable to remove connection. Check that both locations exist.");
        }
    }

    static void traverseCampus() {
        String start = readNonEmptyString("Enter starting location: ");
        if (!campusGraph.hasLocation(start)) {
            System.out.println("Error: Location not found.");
            return;
        }
        System.out.println("Choose traversal type: 1. BFS  2. DFS");
        int type = readInt("Enter choice: ");
        if (type == 1) {
            campusGraph.bfsTraversal(start);
        } else if (type == 2) {
            campusGraph.dfsTraversal(start);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
