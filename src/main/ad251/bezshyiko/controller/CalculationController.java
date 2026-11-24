package ad251.bezshyiko.controller;

import ad251.bezshyiko.calculator.CalculationListener;
import ad251.bezshyiko.calculator.CalculationService;
import ad251.bezshyiko.calculator.Calculator;
import ad251.bezshyiko.command.CalculateCommand;
import ad251.bezshyiko.command.ClearFieldsCommand;
import ad251.bezshyiko.command.ClearResultsCommand;
import ad251.bezshyiko.command.Command;
import ad251.bezshyiko.command.SaveResultsCommand;
import ad251.bezshyiko.model.ResultRecord;
import ad251.bezshyiko.ui.MainFrame;

import java.util.List;

public class CalculationController implements CalculationListener {

    private final MainFrame view;
    private final CalculationService service;
    private final Command calculateCommand;
    private final Command clearFieldsCommand;
    private final Command clearResultsCommand;
    private final Command saveResultsCommand;

    public CalculationController(MainFrame view, CalculationService service) {
        this.view = view;
        this.service = service;
        this.calculateCommand = new CalculateCommand(view, service);
        this.clearFieldsCommand = new ClearFieldsCommand(view);
        this.clearResultsCommand = new ClearResultsCommand(view, service);
        this.saveResultsCommand = new SaveResultsCommand(view, service);

        initListeners();
        service.addListener(this);
        view.configureForm(currentCalculator());
        onRecordsChanged(service.getRecords());
    }

    private void initListeners() {
        view.getTypeBox().addActionListener(e -> view.configureForm(currentCalculator()));
        view.getCalculateButton().addActionListener(e -> calculateCommand.execute());
        view.getClearFieldsButton().addActionListener(e -> clearFieldsCommand.execute());
        view.getClearResultsButton().addActionListener(e -> clearResultsCommand.execute());
        view.getSaveButton().addActionListener(e -> saveResultsCommand.execute());
        view.getAmountField().addActionListener(e -> calculateCommand.execute());
        view.getRateField().addActionListener(e -> calculateCommand.execute());
        view.getTermField().addActionListener(e -> calculateCommand.execute());
        view.getExtraField().addActionListener(e -> calculateCommand.execute());
    }

    private Calculator currentCalculator() {
        return service.getCalculator(view.getSelectedType());
    }

    @Override
    public void onRecordsChanged(List<ResultRecord> records) {
        view.showRecords(records);
    }
}
