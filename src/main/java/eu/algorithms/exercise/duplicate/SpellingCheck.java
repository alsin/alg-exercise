package eu.algorithms.exercise.duplicate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SpellingCheck<T extends Textable> {

    private static final Pattern DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*");
    private static final Pattern WHITE_SPACES = Pattern.compile(".*\\s\\s+.*");

    private final List<T> spellingDoubleCommaComponents = new ArrayList<>();
    private final List<T> spellingWhiteSpacesComponents = new ArrayList<>();
    private final List<T> spellingBeginCapitalComponents = new ArrayList<>();


    public void checkSpelling(final T textable) {
        final String text = textable.text();
        if (DOUBLE_COMMAS.matcher(text).matches()) {
            spellingDoubleCommaComponents.add(textable);
        }
        if (WHITE_SPACES.matcher(text).matches()) {
            spellingWhiteSpacesComponents.add(textable);
        }
        String[] elements = text.split(",");
        for (int i = 0; i < elements.length; i++) {
            String element = elements[i].trim();
            if (element.length() > 0 && Character.getType(element.charAt(0)) == Character.LOWERCASE_LETTER) {
                spellingBeginCapitalComponents.add(textable);
            }
        }
    }

    public List<T> getSpellingDoubleCommaComponents() {
        return spellingDoubleCommaComponents;
    }

    public List<T> getSpellingWhiteSpacesComponents() {
        return spellingWhiteSpacesComponents;
    }

    public List<T> getSpellingBeginCapitalComponents() {
        return spellingBeginCapitalComponents;
    }

}
