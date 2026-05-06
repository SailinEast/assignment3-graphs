public class Vertex {
    private static int idGen = 0;
    private final int id;

    public Vertex() {
        this.id = idGen++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vertex{" +
            "id=" + id +
            '}';
    }
}
