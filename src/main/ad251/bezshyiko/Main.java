package ad251.bezshyiko;

import ad251.bezshyiko.calculator.CalculationService;
import ad251.bezshyiko.controller.CalculationController;
import ad251.bezshyiko.ui.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            CalculationService service = CalculationService.getInstance();
            MainFrame frame = new MainFrame();
            new CalculationController(frame, service);
            frame.setVisible(true);
        });
    }
}
