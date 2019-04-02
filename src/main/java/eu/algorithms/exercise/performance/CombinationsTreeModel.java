package eu.algorithms.exercise.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


public class CombinationsTreeModel extends DefaultTreeModel {

    public CombinationsTreeModel() {
        super(null);
        clear();
    }

    @Override
    public DefaultMutableTreeNode getRoot() {
        return (DefaultMutableTreeNode) super.getRoot();
    }
    
    @Override
    public DefaultMutableTreeNode getChild(Object parent, int index) {
        return (DefaultMutableTreeNode) super.getChild(parent, index);
    }
    
    public void clear() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Combinations");
        root.setAllowsChildren(true);
        setRoot(root);
    }
    
    public List<String> pathToCodes(TreePath path) {
        DefaultMutableTreeNode last = (DefaultMutableTreeNode) path.getLastPathComponent();
        Object[] objects = last.getUserObjectPath();
        List<String> codes = new ArrayList<>();
        // ignore the dummy root
        for(int i = 1; i < objects.length; i++) {
            codes.add((String) objects[i]);
        }
        return codes;
    }

    public DefaultMutableTreeNode init(final List<Combination> combinations) {
        final Node modelTreeRootNode = Node.createTreeFromCombinations(combinations);
        DefaultMutableTreeNode root = insertTree(modelTreeRootNode, null);
        if (root == null) {
            root = new DefaultMutableTreeNode("NULL");
        }

        setRoot(root);

        reload();
        return root;
    }

    private DefaultMutableTreeNode insertTree(final Node modelNode, final DefaultMutableTreeNode parentNode) {
        if (modelNode != null) {
            final DefaultMutableTreeNode node = new DefaultMutableTreeNode(modelNode.getCode());
            node.setAllowsChildren(true);

            if (parentNode != null) {
                parentNode.insert(node, parentNode.getChildCount());
            }

            final Map<String, Node> children = modelNode.getChildren();

            children.forEach((key, value) -> insertTree(value, node));

            return node;
        }
        return null;
    }

    public DefaultMutableTreeNode addCombination(Combination combination) {
        return addCombinationNode(getRoot(), combination, 0);
    }

    private DefaultMutableTreeNode addCombinationNode(DefaultMutableTreeNode parent, 
            Combination combination, int codeIndex) {
        
        String code = combination.getCode(codeIndex);
        DefaultMutableTreeNode child = findChild(parent, code);
        if(child == null) {
            child = new DefaultMutableTreeNode(code);
            child.setAllowsChildren(true);
            insertNodeInto(child, parent, getChildCount(parent));
        }
        if(codeIndex < combination.getCodeCount() - 1) {
            addCombinationNode(child, combination, codeIndex + 1);
        }
        return child;
    }

    private DefaultMutableTreeNode findChild(DefaultMutableTreeNode parent, String code) {
        for(int i = 0; i < getChildCount(parent); i++) {
            DefaultMutableTreeNode child = getChild(parent, i);
            if(child.getUserObject().equals(code)) {
                return child;
            }
        }
        return null;
    }
}
