public class Main {
    private static Graph createGraph(int size) {
        Graph graph = new Graph();

        for (int i = 0; i < size; i++) {
            graph.addVertex(new Vertex());
        }

        for (int i = 0; i < size; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            if (leftChild < size) graph.addEdge(i, leftChild, false);
            if (rightChild < size) graph.addEdge(i, rightChild, false);
        }

        return graph;
    }

    public static void main(String[] args) {
        Graph smallGraph = createGraph(10);
        Graph mediumGraph = createGraph(30);
        Graph largeGraph = createGraph(100);

        Experiment experiment = new Experiment(smallGraph, mediumGraph, largeGraph);

        experiment.runMultipleTests();

        System.out.println("--- Graph Structure ---");
        smallGraph.printGraph();
        smallGraph.setSilent(false);
        System.out.println("\n--- BFS Traversal ---");
        smallGraph.bfs(0);
        System.out.println("\n--- DFS Traversal ---");
        smallGraph.dfs(0);
        System.out.println();
        experiment.printResults();
    }
}
