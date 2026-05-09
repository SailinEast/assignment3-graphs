import java.util.*;

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

    /* BFS traversal (queue)
    1. Set the start vertex node as visited and enqueue its id
    2. While the queue is not empty:
        1. Dequeue the vertex id
        2. Get its neighboring nodes' ids
        3. Enqueue these ids if they are not visited and set each id as visited in the process
        4. Print the vertex id if it's a small graph
     */
    public void bfs(int start) {
        Queue<Integer> destinations = new LinkedList<>();
        boolean[] visited = new boolean[vertices.size()];

        visited[start] = true;
        destinations.add(start);

        while (!destinations.isEmpty()) {
            int toVisit = destinations.poll();
            enqueueIfNotVisited(toVisit, destinations, visited);

            if (vertices.size() <= 10)
                System.out.println("Visited: " + toVisit);
        }
    }

    private void dfsRecursion(int currentId, boolean[] visited) {
        int[] ids = getNeighbors(currentId);
        visited[currentId] = true;

        if (vertices.size() <= 10)
            System.out.println("Visited: " + currentId);

        for (int id : ids) {
            if (!visited[id]) dfsRecursion(id, visited);
        }
    }

    /* DFS traversal (stack)
    Recursion:
        1. Get neighboring nodes' ids
        2. Set the current node as visited
        3. Print the visited node if it's a small graph
        4. If neighboring node is not visited (base case), apply recursion
     */
    public void dfs(int start) {
        boolean[] visited = new boolean[vertices.size()];
        dfsRecursion(start, visited);
    }
}
