package eu.algorithms.exercise.performance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class CodeCombinationsView extends JPanel {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                
                JFrame frame = new JFrame("Code Combinations View");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new CodeCombinationsView());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private CombinationsTableModel tableModel;
    private JTable table;
    
    private CombinationsTreeModel treeModel;
    private JTree tree;
    
    private List<Combination> combinations;

    public CodeCombinationsView() {
        JToolBar toolBar = new JToolBar();
        toolBar.add(new AbstractAction("Load combinations") {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCombinations();
            }
        });
        
        tableModel = new CombinationsTableModel();
        table = new JTable(tableModel);
        
        treeModel = new CombinationsTreeModel();
        tree = new JTree(treeModel) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(300, 400);
            }
        };
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                final long startMillis = System.currentTimeMillis();

                showCombinations(tree.getSelectionPaths());

                logTime(startMillis, "Select time: %ds");
            }
        });
        
        JSplitPane pane = new JSplitPane();
        pane.setLeftComponent(new JScrollPane(tree));
        pane.setRightComponent(new JScrollPane(table));
        
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.PAGE_START);
        add(pane, BorderLayout.CENTER);
    }

    private void logTime(final long startMillis, final String message) {
        final long endMillis = System.currentTimeMillis();

        System.out.println(
                String.format(message,
                        TimeUnit.SECONDS.convert(endMillis - startMillis, TimeUnit.MILLISECONDS))
        );

    }

    private void showCombinations(final TreePath[] paths) {
        List<Combination> filtered = Collections.emptyList();

        if (paths != null) {
            for (final TreePath path : paths) {
                final List<String> codes = treeModel.pathToCodes(path);

                filtered = combinations.stream()
                        .filter(combination -> combination.startsWith(codes))
                        .collect(Collectors.toList());
            }

        }

        loadTableData(filtered);

    }

    private void loadCombinations() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            try {
                loadCombinations(chooser.getSelectedFile());
            }
            catch (IOException e1) {
                JOptionPane.showMessageDialog(this,
                        e1.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadCombinations(File file) throws IOException {
        this.combinations = readCombinationsFromFile(file);//readLines(file);

        loadData(combinations);
    }

    private void loadData(final List<Combination> combinations) {
        tableModel.clear();
        treeModel.clear();

//        Object root = treeModel.getRoot();
//        tree.expandPath(new TreePath(root));

        final long startMillis = System.currentTimeMillis();

        Object root = treeModel.init(combinations);

//        tree.expandPath(new TreePath(new Object[] { root, child }));

//        combinations.forEach(combination -> {
//            final DefaultMutableTreeNode child = treeModel.addCombination(combination);
//            tree.expandPath(new TreePath(new Object[] { root, child }));
//        });

        logTime(startMillis, "Load data time: %ds");
//        tree.setSelectionPath(new TreePath(root));

        final TreePath rootPath = new TreePath(root);
        tree.expandPath(rootPath);
        tree.setSelectionPath(rootPath);

    }

    private void loadTableData(final List<Combination> combinations) {
        tableModel.clear();
        tableModel.setCombinations(combinations);
    }

    private List<Combination> readCombinationsFromFile(final File file) throws IOException {
        final List<Combination> combinations = new ArrayList<>();
        final long startMillis = System.currentTimeMillis();
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if(!line.isEmpty()) {
                    combinations.add(Combination.fromString(line));
                }
            }

            logTime(startMillis, "Reading finished. Time taken: %ds");
            return combinations;
        }
    }

}
