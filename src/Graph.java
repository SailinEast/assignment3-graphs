import java.util.ArrayList;
import java.util.HashMap;

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
}
