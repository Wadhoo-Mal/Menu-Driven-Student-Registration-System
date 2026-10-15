import java.util.Scanner;
class Student{
    int id;
    String name;
    String sec;
    double gpa;

    Student(int id, String name, String sec, double gpa){
        this.id = id;
        this.name = name;
        this.sec = sec;
        this.gpa = gpa;
    }

    void displayData(){
        System.out.print("ID: " + id
                            + " | Name: " + name
                            + " | Section: " + sec
                            + " | GPA: " + gpa
        );
    }
}

// Node class
class Node{
    Student data;
    Node next;
    Node prev;

    Node(Student data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Doubly linkedlist class
class DoubLinkedList{
    Node head;
    Node tail;

    DoubLinkedList(){
        head = null;
        tail = null;
    }

// Add student in the front
    void addStudentFirst(Student data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

// Delete student from front
    void deleteStudentFirst(){
        if(head == null){
            System.out.println("List is empty.");
            return;
        }
        if(head == tail){
            head = tail = null;
            System.out.println("First student deleted successfully.");
            return;
        }
        head = head.next;
        head.prev = null;
        System.out.println("First student deleted successfully.");
    }

// Add student in the end
    void addStudentLast(Student data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }

// Delete student from the end
    void deleteStudentLast(){
        if(head == null){
            System.out.println("List is empty.");
            return;
        }
        if(head == tail){
            head = tail = null;
            System.out.println("Last student deleted successfully.");
            return;
        }
        tail = tail.prev;
        tail.next = null;
        System.out.println("Last student deleted successfully.");
    }

// Search student by ID
    Student searchById(int id){
        Node current = head;
        while(current != null){
            if(current.data.id == id){
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    void searchStudent(int id){
        Student student = searchById(id);
        if(student == null){
            System.out.println("Student not found.");
            return;
        }
        student.displayData();
        System.out.println();
    }

// Delete the targeted student from the list
    void deleteStudent(int id){
        Node current = head;
        while(current != null && current.data.id != id){
            current = current.next;
        }
        if(current == null){
            System.out.println("Student not found.");
            return;
        }
        if(current == head){
            deleteStudentFirst();
            return;
        }
        if(current == tail){
            deleteStudentLast();
            return;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        System.out.println("Student deleted successfully.");
    }

// Add student before the targeted student
    void addStudentBefore(int targetId, Student data){
        Node current = head;
        while(current != null && current.data.id != targetId){
            current = current.next;
        }
        if(current == null){
            System.out.println("Target student not found.");
            return;
        }
        if(current == head){
            addStudentFirst(data);
            System.out.println("Student added before target successfully.");
            return;
        }
        Node newNode = new Node(data);
        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        System.out.println("Student added before target successfully.");
    }

// Add student after the targeted student
    void addStudentAfter(int targetId, Student data){
        Node current = head;
        while(current != null && current.data.id != targetId){
            current = current.next;
        }
        if(current == null){
            System.out.println("Target student not found.");
            return;
        }
        if(current == tail){
            addStudentLast(data);
            System.out.println("Student added after target successfully.");
            return;
        }
        Node newNode = new Node(data);
        newNode.prev = current;
        newNode.next = current.next;
        current.next.prev = newNode;
        current.next = newNode;
        System.out.println("Student added after target successfully.");
    }

// Update the targeted student
    void updateStudent(int id, Student data){
        Student current = searchById(id);
        if(current == null){
            System.out.println("Student not found.");
            return;
        }
        current.name = data.name;
        current.sec = data.sec;
        current.gpa = data.gpa;
        System.out.println("Student updated successfully.");
    }

// Display the list of students
    void display(){
        if(head == null){
            System.out.println("List is empty.");
            return;
        }
        System.out.print("null <-> ");
        Node current = head;
        while(current != null){
            current.data.displayData();
            System.out.print(" <-> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class DLinkedListWithTailStudent{

    static Student inputStudent(Scanner sc){
        System.out.println("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter name: ");
        String name = sc.nextLine();

        System.out.println("Enter Section: ");
        String sec = sc.nextLine();

        System.out.println("Enter CGPA: ");
        double gpa = sc.nextDouble();

        return new Student(id, name, sec, gpa);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DoubLinkedList list = new DoubLinkedList();
        int choice;

        do{
            System.out.println("\n===== STUDENT DOUBLY LINKED LIST =====");
            System.out.println("1. Add Student in front");
            System.out.println("2. Add Student in the end");
            System.out.println("3. Delete Student from front");
            System.out.println("4. Delete Student from back");
            System.out.println("5. Add Student before any student");
            System.out.println("6. Add Student after any student");
            System.out.println("7. Search Student");
            System.out.println("8. Delete any student");
            System.out.println("9. Update student");
            System.out.println("10. Display all students");
            System.out.println("11. Exit");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    list.addStudentFirst(inputStudent(sc));
                    break;

                case 2:
                    list.addStudentLast(inputStudent(sc));
                    break;

                case 3:
                    list.deleteStudentFirst();
                    break;

                case 4:
                    list.deleteStudentLast();
                    break;

                case 5:
                    System.out.println("Enter target student's ID: ");
                    int targetId = sc.nextInt();
                    System.out.println("Enter new student details: ");
                    Student s = inputStudent(sc);
                    list.addStudentBefore(targetId, s);
                    break;

                case 6:
                    System.out.println("Enter target student's ID: ");
                    targetId = sc.nextInt();
                    System.out.println("Enter new student details: ");
                    Student s1 = inputStudent(sc);
                    list.addStudentAfter(targetId, s1);
                    break;

                case 7:
                    System.out.println("Enter student ID to search: ");
                    int searchId = sc.nextInt();
                    list.searchStudent(searchId);
                    break;

                case 8:
                    System.out.println("Enter student ID to delete: ");
                    int deleteId = sc.nextInt();
                    list.deleteStudent(deleteId);
                    break;

                case 9:
                    System.out.println("Enter student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Student existing = list.searchById(updateId);
                    if(existing == null){
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.println("Enter new name: ");
                    String name = sc.nextLine();

                    System.out.println("Enter new Section: ");
                    String sec = sc.nextLine();

                    System.out.println("Enter new CGPA: ");
                    double gpa = sc.nextDouble();

                    Student updated = new Student(updateId, name, sec, gpa);
                    list.updateStudent(updateId, updated);
                    break;

                case 10:
                    list.display();
                    break;

                case 11:
                    System.out.println("Program exited.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-11.");
            }
        }while(choice != 11);

        sc.close();
    }
}
