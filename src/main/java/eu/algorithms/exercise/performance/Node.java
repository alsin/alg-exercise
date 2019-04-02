package eu.algorithms.exercise.performance;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class which builds up a tree from the input code combinations.
 */
public class Node {

    final String code;

    Map<String, Node> children;

    private Node(final String code) {
        this.code = code;
    }

    public Node addCode(final String code) {
        if (children == null) {
            children = new HashMap<>();
        }

        return children.computeIfAbsent(code, Node::new);
    }

    public Map<String, Node> getChildren() {
        return children == null ? Collections.emptyMap() : children;
    }

    public String getCode() {
        return code;
    }

    public static Node createTreeFromCombinations(final List<Combination> combinations) {
        final Node root = new Node("Combinations");
        combinations.forEach(combination -> {
            final int codeCount = combination.getCodeCount();
            Node curNode = root;
            for (int i = 0; i < codeCount; i++) {
                curNode = curNode.addCode(combination.getCode(i));
            }
        });
        return root;
    }
}
