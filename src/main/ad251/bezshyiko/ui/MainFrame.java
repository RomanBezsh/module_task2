package ad251.bezshyiko.ui;

import ad251.bezshyiko.calculator.Calculator;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ExtraInputType;
import ad251.bezshyiko.model.ResultRecord;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class MainFrame extends JFrame {

    private final JComboBox<CalculationType> typeBox = new JComboBox<>(CalculationType.values());
    private final JLabel amountLabel = new JLabel("Сума інвестиції, грн:");
    private final JLabel rateLabel = new JLabel("Річна ставка, %:");
    private final JLabel termLabel = new JLabel("Термін, років:");
    private final JLabel extraLabel = new JLabel("Додатково:");
    private final JTextField amountField = new JTextField(14);
    private final JTextField rateField = new JTextField(14);
    private final JTextField termField = new JTextField(14);
    private final JTextField extraField = new JTextField(18);

    private final JButton calculateButton = new JButton("Розрахувати");
    private final JButton clearFieldsButton = new JButton("Очистити поля");
    private final JButton clearResultsButton = new JButton("Очистити результати");
    private final JButton saveButton = new JButton("Зберегти у файл");

    private final JLabel hintLabel = new JLabel(" ");
    private final JLabel statusLabel = new JLabel("Розрахунків: 0");
    private final ResultsTableModel tableModel = new ResultsTableModel();
    private final JTable table = new JTable(tableModel);
    private final ChartPanel chartPanel = new ChartPanel();

    public MainFrame() {
        super("Інвестиційний калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));

        add(createInputPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setPreferredSize(new Dimension(1180, 720));
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout(14, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Параметри розрахунку"));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        addRow(form, c, 0, new JLabel("Тип розрахунку:"), typeBox);
        addRow(form, c, 1, amountLabel, amountField);
        addRow(form, c, 2, rateLabel, rateField);
        addRow(form, c, 3, termLabel, termField);
        addRow(form, c, 4, extraLabel, extraField);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.weightx = 1.0;
        hintLabel.setForeground(new Color(96, 102, 117));
        hintLabel.setFont(hintLabel.getFont().deriveFont(Font.PLAIN, 12f));
        form.add(hintLabel, c);

        JPanel actions = new JPanel(new GridBagLayout());
        GridBagConstraints a = new GridBagConstraints();
        a.insets = new Insets(4, 4, 4, 4);
        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 1.0;
        actions.add(calculateButton, a);
        a.gridy = 1;
        actions.add(clearFieldsButton, a);

        panel.add(form, BorderLayout.CENTER);
        panel.add(actions, BorderLayout.EAST);
        return panel;
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, JLabel label, java.awt.Component field) {
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 1;
        c.weightx = 0.0;
        panel.add(label, c);
        c.gridx = 1;
        c.weightx = 1.0;
        panel.add(field, c);
    }

    private JPanel createTablePanel() {
        table.setRowHeight(24);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        int[] widths = {40, 220, 90, 90, 110, 170, 150};
        for (int i = 0; i < widths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Результати розрахунків"));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createChartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Візуалізація"));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCenterPanel() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createTablePanel(), createChartPanel());
        splitPane.setResizeWeight(0.58);
        splitPane.setDividerLocation(650);
        splitPane.setBorder(null);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(splitPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(new EmptyBorder(4, 0, 0, 0));
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 12f));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttons.add(clearResultsButton);
        buttons.add(saveButton);

        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(buttons, BorderLayout.EAST);
        return panel;
    }

    public void configureForm(Calculator calculator) {
        setFieldEnabled(amountLabel, amountField, calculator.usesAmount());
        setFieldEnabled(rateLabel, rateField, calculator.usesRate());
        setFieldEnabled(termLabel, termField, calculator.usesTerm());

        boolean extraEnabled = calculator.getExtraInputType() != ExtraInputType.NONE;
        setFieldEnabled(extraLabel, extraField, extraEnabled);
        extraLabel.setText(extraEnabled ? calculator.getExtraLabel() + ":" : "Додатково:");
        extraField.setToolTipText(calculator.getExtraHint());
        hintLabel.setText(calculator.getHint());

        if (calculator.usesAmount()) {
            amountField.requestFocusInWindow();
        } else if (extraEnabled) {
            extraField.requestFocusInWindow();
        }
    }

    private void setFieldEnabled(JLabel label, JTextField field, boolean enabled) {
        label.setEnabled(enabled);
        field.setEnabled(enabled);
    }

    public void showRecords(List<ResultRecord> records) {
        tableModel.setRecords(records);
        chartPanel.setRecords(records);
        String lastResult = records.isEmpty()
                ? ""
                : "   |   Останній результат: " + records.get(records.size() - 1).getResultText();
        statusLabel.setText("Розрахунків: " + records.size() + lastResult);
    }

    public void selectLastRow() {
        int lastRow = tableModel.getRowCount() - 1;
        if (lastRow >= 0) {
            table.setRowSelectionInterval(lastRow, lastRow);
            table.scrollRectToVisible(table.getCellRect(lastRow, 0, true));
        }
    }

    public void clearForm() {
        amountField.setText("");
        rateField.setText("");
        termField.setText("");
        extraField.setText("");
        if (amountField.isEnabled()) {
            amountField.requestFocusInWindow();
        } else {
            extraField.requestFocusInWindow();
        }
    }

    public boolean confirmClearResults() {
        int answer = JOptionPane.showConfirmDialog(this,
                "Очистити всі результати розрахунків?", "Підтвердження",
                JOptionPane.YES_NO_OPTION);
        return answer == JOptionPane.YES_OPTION;
    }

    public Optional<File> chooseSaveFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Зберегти результати розрахунків");
        chooser.setSelectedFile(new File("investment_results.txt"));
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return Optional.empty();
        }
        File file = chooser.getSelectedFile();
        if (!file.getName().toLowerCase(Locale.ROOT).endsWith(".txt")) {
            file = new File(file.getParentFile(), file.getName() + ".txt");
        }
        return Optional.of(file);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Помилка", JOptionPane.ERROR_MESSAGE);
    }

    public void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Інформація", JOptionPane.INFORMATION_MESSAGE);
    }

    public CalculationType getSelectedType() {
        return (CalculationType) typeBox.getSelectedItem();
    }

    public String getAmountText() {
        return amountField.getText().trim();
    }

    public String getRateText() {
        return rateField.getText().trim();
    }

    public String getTermText() {
        return termField.getText().trim();
    }

    public String getExtraText() {
        return extraField.getText().trim();
    }

    public JComboBox<CalculationType> getTypeBox() {
        return typeBox;
    }

    public JButton getCalculateButton() {
        return calculateButton;
    }

    public JButton getClearFieldsButton() {
        return clearFieldsButton;
    }

    public JButton getClearResultsButton() {
        return clearResultsButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JTextField getRateField() {
        return rateField;
    }

    public JTextField getTermField() {
        return termField;
    }

    public JTextField getExtraField() {
        return extraField;
    }
}
