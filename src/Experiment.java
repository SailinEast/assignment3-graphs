public class Experiment {
    private long dfsTime;
    private long bfsTime;
    private long[] smallTime = new long[2];
    private long[] mediumTime = new long[2];
    private long[] largeTime = new long[2];
    private final Graph small;
    private final Graph medium;
    private final Graph large;

    public Experiment(Graph s, Graph m, Graph l) {
        this.small = s;
        this.medium = m;
        this.large = l;
    }

    public void runTraversals(Graph g) {
        long start1 = System.nanoTime();
        g.bfs(0);
        long end1 = System.nanoTime();
        bfsTime = end1 - start1;

        long start2 = System.nanoTime();
        g.dfs(0);
        long end2 = System.nanoTime();
        dfsTime = end2 - start2;
    }

    private void warmup() {
        Graph graph = new Graph();

        for (int i = 0; i < 50; i++) {
            graph.addVertex(new Vertex());
        }

        for (int i = 0; i < 50; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            if (leftChild < 50) graph.addEdge(i, leftChild, false);
            if (rightChild < 50) graph.addEdge(i, rightChild, false);
        }

        for (int i = 0; i < 15000; i++) {
            runTraversals(graph);
        }
    }

    private long[] runTests(int iterations, Graph graph) {
        long[] avgTime = new long[2];
        for (int i = 0; i < iterations; i++) {
            runTraversals(graph);
            avgTime[0] += bfsTime;
            avgTime[1] += dfsTime;
        }
        avgTime[0] = avgTime[0] / iterations;
        avgTime[1] = avgTime[1] / iterations;

        return avgTime;
    }

    public void runMultipleTests() {
        warmup();

        smallTime = runTests(100000, small);
        mediumTime = runTests(100000, medium);
        largeTime = runTests(100000, large);
    }

    public void printResults() {
        String format = "| %-15s | %-15d | %-15d |%n";

        System.out.printf("| %-15s | %-15s | %-15s |%n", "SIZE", "BFS (ns)", "DFS (ns)");
        System.out.println("|-----------------------------------------------------|");

        System.out.printf(format, "10", smallTime[0], smallTime[1]);
        System.out.printf(format, "30", mediumTime[0], mediumTime[1]);
        System.out.printf(format, "100", largeTime[0], largeTime[1]);
    }
}
