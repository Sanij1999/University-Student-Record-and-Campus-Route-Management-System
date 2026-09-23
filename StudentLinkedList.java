import java.util.ArrayList;
import java.util.List;

class StudentNode {
    Student data;
    StudentNode next;

    StudentNode(Student data) {
        this.data = data;
    }
}

public class StudentLinkedList {
    private StudentNode head;
    private int size;

    public boolean add(Student student) {
        if (search(student.getId()) != null) {
            return false;
        }
        StudentNode node = new StudentNode(student);
        if (head == null) {
            head = node;
        } else {
            StudentNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
        return true;
    }

    public Student search(String id) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.data.getId().equalsIgnoreCase(id)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean update(String id, String name, String programme, double marks) {
        Student s = search(id);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setProgramme(programme);
        s.setMarks(marks);
        return true;
    }

    public boolean delete(String id) {
        if (head == null) {
            return false;
        }
        if (head.data.getId().equalsIgnoreCase(id)) {
            head = head.next;
            size--;
            return true;
        }
        StudentNode prev = head;
        StudentNode curr = head.next;
        while (curr != null) {
            if (curr.data.getId().equalsIgnoreCase(id)) {
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }
        StudentNode temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        StudentNode temp = head;
        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }
        return list;
    }

    public int getSize() {
        return size;
    }
}
