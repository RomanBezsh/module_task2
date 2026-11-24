package ad251.bezshyiko.ui;

import ad251.bezshyiko.model.ResultRecord;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ResultsTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {
            "№", "Тип розрахунку", "Сума", "Ставка, %", "Термін, років", "Додатково", "Результат"
    };

    private final List<ResultRecord> records = new ArrayList<>();

    public void setRecords(List<ResultRecord> records) {
        this.records.clear();
        this.records.addAll(records);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return records.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ResultRecord record = records.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return record.getNumber();
            case 1:
                return record.getType().getDisplayName();
            case 2:
                return record.getAmount();
            case 3:
                return record.getRate();
            case 4:
                return record.getTerm();
            case 5:
                return record.getExtra();
            case 6:
                return record.getResultText();
            default:
                return "";
        }
    }
}
