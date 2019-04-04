package eu.algorithms.exercise.performance;

import java.util.*;
import java.util.function.Consumer;

/**
 * Class which builds up a tree from the input code combinations.
 */
public class Node {

    private final String code;

    private Map<String, Node> children;

    private Node(final String code) {
        this.code = code;
    }

    public Map<String, Node> getChildren() {
        if (children == null) {
            children = new HashMap<>();
        }
        return children;
    }

    public String getCode() {
        return code;
    }

    public Node addCombination(final Combination combination) {
        final List<String> codes = combination.getCodes();
        Node curNode = this;
        for (int i = 0; i < codes.size(); i++) {
            final String code = codes.get(i);
            curNode = curNode.getChildren().computeIfAbsent(code, Node::new);
        }
        return this;
    }

    public static List<Combination> getCombinations(final Node rootNode, final List<String> codes) {
        Node curNode = rootNode;
        for (String code : codes) {
            final Node nextNode = curNode.getChildren().get(code);
            if (nextNode != null) {
                curNode = nextNode;
            } else {
                curNode = rootNode;
                break;
            }
        }

        final List<Combination> result = new ArrayList<>();
        collectCombinations(curNode.getChildren().values(), codes,
                codesList -> result.add(Combination.fromList(codesList)));
        return result;
    }

    private static void collectCombinations(final Collection<Node> nodes,
                                            final List<String> codes,
                                            final Consumer<List<String>> consumer) {

        if (nodes.isEmpty()) {
            consumer.accept(codes);
        } else {
            for (Node childNode : nodes) {
                final List<String> childList = new ArrayList<>(codes);
                childList.add(childNode.getCode());
                collectCombinations(childNode.getChildren().values(), childList, consumer);
            }
        }
    }

    public static Node createNode(final String code) {
        return new Node(code);
    }

}
