package ad251.bezshyiko.command;

import ad251.bezshyiko.calculator.CalculationService;
import ad251.bezshyiko.io.ResultExporter;
import ad251.bezshyiko.model.ResultRecord;
import ad251.bezshyiko.ui.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SaveResultsCommand implements Command {

    private final MainFrame view;
    private final CalculationService service;

    public SaveResultsCommand(MainFrame view, CalculationService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void execute() {
        List<ResultRecord> records = service.getRecords();
        if (records.isEmpty()) {
            view.showInfo("Немає результатів для збереження.");
            return;
        }
        Optional<File> selectedFile = view.chooseSaveFile();
        if (selectedFile.isEmpty()) {
            return;
        }
        File file = selectedFile.get();
        try {
            ResultExporter.export(file, records);
            view.showInfo("Результати збережено у файл:\n" + file.getAbsolutePath());
        } catch (IOException ex) {
            view.showError("Не вдалося зберегти файл: " + ex.getMessage());
        }
    }
}
