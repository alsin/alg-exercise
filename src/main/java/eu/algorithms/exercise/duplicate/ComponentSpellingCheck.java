package eu.algorithms.exercise.duplicate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ComponentSpellingCheck {

    private static final Pattern DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*");
    private static final Pattern WHITE_SPACES = Pattern.compile(".*\\s\\s+.*");

    private final List<Component> spellingDoubleCommaComponents = new ArrayList<>();
	private final List<Component> spellingWhiteSpacesComponents = new ArrayList<>();
	private final List<Component> spellingBeginCapitalComponents = new ArrayList<>();

    public void checkSpelling(Component component) {
    	if (DOUBLE_COMMAS.matcher(component.getDescription()).matches()) {
    	    spellingDoubleCommaComponents.add(component);
    	}
    	if (WHITE_SPACES.matcher(component.getDescription()).matches()) {
    	    spellingWhiteSpacesComponents.add(component);
    	}
    	String[] elements = component.getDescription().split(",");
    	for (int i = 0; i < elements.length; i++) {
    		String element = elements[i].trim();
    		if (element.length() > 0 && Character.getType(element.charAt(0)) == Character.LOWERCASE_LETTER) {
    		    spellingBeginCapitalComponents.add(component);
    		}
    	}
    }

	public List<Component> getSpellingDoubleCommaComponents() {
		return spellingDoubleCommaComponents;
	}

	public List<Component> getSpellingWhiteSpacesComponents() {
		return spellingWhiteSpacesComponents;
	}

	public List<Component> getSpellingBeginCapitalComponents() {
		return spellingBeginCapitalComponents;
	}
}
