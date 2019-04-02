package eu.algorithms.exercise.duplicate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SpellingCheck {

    private static final Pattern DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*");
    private static final Pattern WHITE_SPACES = Pattern.compile(".*\\s\\s+.*");

    private final List<String> spellingDoubleCommaComponents = new ArrayList<>();
	private final List<String> spellingWhiteSpacesComponents = new ArrayList<>();
	private final List<String> spellingBeginCapitalComponents = new ArrayList<>();

    public void checkSpelling(String text) {
    	if (DOUBLE_COMMAS.matcher(text).matches()) {
    	    spellingDoubleCommaComponents.add(text);
    	}
    	if (WHITE_SPACES.matcher(text).matches()) {
    	    spellingWhiteSpacesComponents.add(text);
    	}
    	String[] elements = text.split(",");
    	for (int i = 0; i < elements.length; i++) {
    		String element = elements[i].trim();
    		if (element.length() > 0 && Character.getType(element.charAt(0)) == Character.LOWERCASE_LETTER) {
    		    spellingBeginCapitalComponents.add(text);
    		}
    	}
    }

	public List<String> getSpellingDoubleCommaComponents() {
		return spellingDoubleCommaComponents;
	}

	public List<String> getSpellingWhiteSpacesComponents() {
		return spellingWhiteSpacesComponents;
	}

	public List<String> getSpellingBeginCapitalComponents() {
		return spellingBeginCapitalComponents;
	}
}
