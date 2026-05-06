import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    ArrayList<Vertex> vertices = new ArrayList<>();
    HashMap<Integer, ArrayList<Edge>> adjList = new HashMap<>();

    public void addVertex(Vertex v) {
        vertices.add(v.getId(), v);
        adjList.put(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        Edge edge = new Edge(vertices.get(from), vertices.get(to));
        adjList.get(from).add(edge);
    }

    public void printGraph() {
        for (Vertex v : vertices) {
            System.out.println("Vertex " + v.getId() + " is connected to: " + adjList.get(v.getId()));
        }
    }
}
