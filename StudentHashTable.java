import java.util.LinkedList;

public class StudentHashTable {
    private static final int CAPACITY = 50;
    private LinkedList<Student>[] table;

    @SuppressWarnings("unchecked")
    public StudentHashTable() {
        table = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String id) {
        int hashValue = Math.abs(id.hashCode());
        return hashValue % CAPACITY;
    }

    public void insert(Student student) {
        int index = hash(student.getId());
        table[index].add(student);
    }

    public boolean delete(String id) {
        int index = hash(id);
        LinkedList<Student> bucket = table[index];
        for (Student s : bucket) {
            if (s.getId().equalsIgnoreCase(id)) {
                bucket.remove(s);
                return true;
            }
        }
        return false;
    }

    public Student search(String id) {
        int index = hash(id);
        for (Student s : table[index]) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
