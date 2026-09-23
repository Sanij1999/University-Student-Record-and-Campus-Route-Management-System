import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {
    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    public boolean addLocation(String location) {
        if (adjList.containsKey(location)) {
            return false;
        }
        adjList.put(location, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String location) {
        if (!adjList.containsKey(location)) {
            return false;
        }
        adjList.remove(location);
        for (List<String> neighbours : adjList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    public boolean addConnection(String loc1, String loc2) {
        if (!adjList.containsKey(loc1) || !adjList.containsKey(loc2)) {
            return false;
        }
        if (adjList.get(loc1).contains(loc2)) {
            return false;
        }
        adjList.get(loc1).add(loc2);
        adjList.get(loc2).add(loc1);
        return true;
    }

    public boolean removeConnection(String loc1, String loc2) {
        if (!adjList.containsKey(loc1) || !adjList.containsKey(loc2)) {
            return false;
        }
        adjList.get(loc1).remove(loc2);
        adjList.get(loc2).remove(loc1);
        return true;
    }

    public void displayConnections() {
        if (adjList.isEmpty()) {
            System.out.println("No campus locations available.");
            return;
        }
        for (String location : adjList.keySet()) {
            System.out.println(location + " -> " + adjList.get(location));
        }
    }

    public boolean hasLocation(String location) {
        return adjList.containsKey(location);
    }

    public void bfsTraversal(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Location not found.");
            return;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " ");
            for (String neighbour : adjList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();
    }

    public void dfsTraversal(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Location not found.");
            return;
        }
        Set<String> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsRec(start, visited);
        System.out.println();
    }

    private void dfsRec(String current, Set<String> visited) {
        visited.add(current);
        System.out.print(current + " ");
        for (String neighbour : adjList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsRec(neighbour, visited);
            }
        }
    }
}
