import java.util.LinkedList;
import java.util.Queue;

public class ServiceQueue {
    private Queue<String> queue = new LinkedList<>();

    public void addRequest(String studentId) {
        queue.add(studentId);
    }

    public String processNext() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        for (String id : queue) {
            System.out.println(id);
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
