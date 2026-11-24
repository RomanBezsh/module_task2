package ad251.bezshyiko.command;

import ad251.bezshyiko.ui.MainFrame;

public class ClearFieldsCommand implements Command {

    private final MainFrame view;

    public ClearFieldsCommand(MainFrame view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.clearForm();
    }
}
