package eu.algorithms.exercise.performance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class CombinationsTableModel extends AbstractTableModel {

    private List<Combination> combinations = new ArrayList<>();
    private int columnCount;

    public void setCombinations(final List<Combination> combinations) {
        this.combinations = combinations;
        if (!this.combinations.isEmpty()) {
            final int newColumnCount = combinations.get(0).getCodeCount();
            if (newColumnCount != this.columnCount) {
                this.columnCount = newColumnCount;
                fireTableStructureChanged();
            }
            fireTableRowsInserted(0, this.combinations.size() - 1);
        }
    }

    @Override
    public int getRowCount() {
        return combinations.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Combination combination = combinations.get(rowIndex);
        return combination.getCode(columnIndex);
    }

    public void clear() {
        int size = this.combinations.size();
        if(size > 0) {
            this.combinations = Collections.emptyList();
            fireTableRowsDeleted(0, size - 1);
        }
    }
}
