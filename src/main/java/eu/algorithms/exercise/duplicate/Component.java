package eu.algorithms.exercise.duplicate;


public class Component {

    private final String type;
    private final String size;

    public Component(String type, String size) {
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getDescription() {
        return type + ", " + size;
    }
}
