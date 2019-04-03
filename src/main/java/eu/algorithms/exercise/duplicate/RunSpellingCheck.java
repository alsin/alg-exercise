package eu.algorithms.exercise.duplicate;

import java.util.*;


public class RunSpellingCheck {

    public static void main(String[] args) {
        String[] types = { "Pipe", "flange", "Reducer" };
        String[] sizes = { "1'", "2'", ",3" };

        List<Component> components = new ArrayList<>();
        for(String type : types) {
            for(String size : sizes) {
                components.add(new Component(type, size));
            }
        }

        final SpellingCheck<StringComponent> check = new SpellingCheck<>();
        for(String type : types) {
            check.checkSpelling(new StringComponent(type));
        }

        for(String size : sizes) {
            check.checkSpelling(new StringComponent(size));
        }

        final List<StringComponent> incorrect = new ArrayList<>();
        incorrect.addAll(check.getSpellingDoubleCommaComponents());
        incorrect.addAll(check.getSpellingBeginCapitalComponents());
        incorrect.addAll(check.getSpellingWhiteSpacesComponents());
        System.out.println("Incorrect attributes: " + incorrect);

        final SpellingCheck<Component> componentCheck = new SpellingCheck<>();
        for(Component component : components) {
            componentCheck.checkSpelling(component);
        }
        List<Component> incorrectComponents = new ArrayList<>();
        incorrectComponents.addAll(componentCheck.getSpellingDoubleCommaComponents());
        incorrectComponents.addAll(componentCheck.getSpellingBeginCapitalComponents());
        incorrectComponents.addAll(componentCheck.getSpellingWhiteSpacesComponents());
        System.out.println("Incorrect components:");
        for(Component component : incorrectComponents) {
            System.out.println(component.getDescription());
        }

        Set<Component> set = new HashSet<>();
        set.add(new Component(types[1], sizes[0]));
        set.add(new Component(types[1], sizes[0]));
        set.add(new Component(types[1], sizes[2]));
        System.out.println("Similar components: " + findSameComponents(components, set));
    }

    /**
     * @param componentsA first collection of components
     * @param componentsB second collection of components
     * @return The components which can be found in both collections
     *         with all their attributes the same.
     */
    public static Set<Component> findSameComponents(
            Collection<? extends Component> componentsA,
            Collection<? extends Component> componentsB) {
        final Set<Component> componentsACopy = new HashSet<>(componentsA);
        componentsACopy.retainAll(componentsB);
        return componentsACopy;
    }

}
