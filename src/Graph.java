import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    ArrayList<Vertex> vertices = new ArrayList<>();
    HashMap<Integer, ArrayList<Edge>> adjList = new HashMap<>();

    public void addVertex(Vertex v) {
        vertices.add(v);
        adjList.put(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        Edge edge = new Edge(vertices.get(from), vertices.get(to));
        adjList.get(from).add(edge);
    }

    public void addEdge(int from, int to, boolean isDirected) {
        if (isDirected) {
            addEdge(from, to);
        } else {
            Edge edge = new Edge(vertices.get(from), vertices.get(to));
            Edge edgeBack = new Edge(vertices.get(to), vertices.get(from));
            adjList.get(from).add(edge);
            adjList.get(to).add(edgeBack);
        }
    }

    public void printGraph() {
        for (Vertex v : vertices) {
            System.out.println("Vertex " + v.getId() + " is connected to: " + adjList.get(v.getId()));
        }
    }

    private int[] getNeighbors(int id) {
        ArrayList<Edge> edges = adjList.get(id);
        int[] neighbors = new int[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            neighbors[i] = edges.get(i).getDestination().getId();
        }
        return neighbors;
    }

    private void enqueueIfNotVisited(int id, Queue<Integer> destinations, boolean[] visited) {
        for (int neighbor : getNeighbors(id)) {
            if (!visited[neighbor]) {
                destinations.offer(neighbor);
                visited[neighbor] = true;
            }
        }
        visited[id] = true;
    }

    public void bfs(int start) {
        Queue<Integer> destinations = new LinkedList<>();
        boolean[] visited = new boolean[vertices.size()];

        visited[start] = true;
        destinations.add(start);

        while (!destinations.isEmpty()) {
            int toVisit = destinations.poll();
            enqueueIfNotVisited(toVisit, destinations, visited);
            System.out.println("Visited: " + toVisit);
        }
    }
}
