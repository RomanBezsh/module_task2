package ad251.bezshyiko.command;

import ad251.bezshyiko.calculator.CalculationService;
import ad251.bezshyiko.ui.MainFrame;

public class ClearResultsCommand implements Command {

    private final MainFrame view;
    private final CalculationService service;

    public ClearResultsCommand(MainFrame view, CalculationService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void execute() {
        if (service.getRecords().isEmpty()) {
            return;
        }
        if (view.confirmClearResults()) {
            service.clear();
        }
    }
}
