package eu.algorithms.exercise.duplicate;

public class StringComponent implements Textable {

    private final String text;

    public StringComponent(final String txt) {
        this.text = txt;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
