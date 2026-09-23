import java.util.Stack;

public class ActionStack {
    private Stack<String> stack = new Stack<>();

    public void push(String action) {
        stack.push(action);
    }

    public String pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    public void displayActions() {
        if (stack.isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }
        Stack<String> temp = new Stack<>();
        temp.addAll(stack);
        while (!temp.isEmpty()) {
            System.out.println(temp.pop());
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
