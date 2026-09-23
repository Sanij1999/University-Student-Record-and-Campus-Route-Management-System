class BSTNode {
    Student data;
    BSTNode left;
    BSTNode right;

    BSTNode(Student data) {
        this.data = data;
    }
}

public class StudentBST {
    private BSTNode root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private BSTNode insertRec(BSTNode node, Student student) {
        if (node == null) {
            return new BSTNode(student);
        }
        int cmp = student.getId().compareTo(node.data.getId());
        if (cmp < 0) {
            node.left = insertRec(node.left, student);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    public void delete(String id) {
        root = deleteRec(root, id);
    }

    private BSTNode deleteRec(BSTNode node, String id) {
        if (node == null) {
            return null;
        }
        int cmp = id.compareTo(node.data.getId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, id);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            BSTNode successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getId());
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Student search(String id) {
        BSTNode node = root;
        while (node != null) {
            int cmp = id.compareTo(node.data.getId());
            if (cmp == 0) {
                return node.data;
            }
            node = cmp < 0 ? node.left : node.right;
        }
        return null;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in BST.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }
}
