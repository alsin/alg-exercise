package eu.algorithms.exercise.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Combination {

    private List<String> codes;

    private Combination(final List<String> codes) {
        this.codes = codes;
    }

    public int getCodeCount() {
        return codes.size();
    }

    public String getCode(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        return index < codes.size() ? codes.get(index) : "";
    }

    public List<String> getCodes() {
        return codes;
    }

    public static Combination fromList(final List<String> codes) {
        return new Combination(codes);
    }

    public static Combination fromString(final String str) {
        Objects.requireNonNull(str, "str is null!");
        final String trimmed = str.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("str is empty!");
        }

        final int codesCount = 1 + (str.length() - 3) / 2;
        final List<String> codes = new ArrayList<>();

        for (int i = 0; i < codesCount; i++) {
            final int start = (i != 0 ? 1 : 0) + 2*i;
            final int end = 3 + 2*i;
            codes.add(str.substring(start, end));
        }
        return new Combination(codes);
    }

}
