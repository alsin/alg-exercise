package eu.algorithms.exercise.duplicate;


import java.util.Objects;

public class Component implements Textable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return type.equals(component.type) &&
                size.equals(component.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size);
    }

    @Override
    public String toString() {
        return "Component{" +
                "type='" + type + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public String text() {
        return getDescription();
    }
}
