public abstract class Shape {

    private final String id;
    protected Renderer renderer;

    public Shape(String id, Renderer renderer) {
        this.id = id;
        this.renderer = renderer;
    }

    public String getId() {
        return id;
    }

    public void setImplementation(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String execute();
}