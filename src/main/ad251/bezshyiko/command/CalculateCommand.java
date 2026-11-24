package ad251.bezshyiko.command;

import ad251.bezshyiko.calculator.CalculationService;
import ad251.bezshyiko.calculator.Calculator;
import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.ExtraInputType;
import ad251.bezshyiko.ui.MainFrame;
import ad251.bezshyiko.util.FormatUtils;
import ad251.bezshyiko.validation.InputValidator;
import ad251.bezshyiko.validation.ValidationException;

import java.util.stream.Collectors;

public class CalculateCommand implements Command {

    private final MainFrame view;
    private final CalculationService service;

    public CalculateCommand(MainFrame view, CalculationService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void execute() {
        Calculator calculator = service.getCalculator(view.getSelectedType());
        try {
            CalculationInput input = buildInput(calculator);
            String amountText = calculator.usesAmount() ? FormatUtils.format(input.getAmount()) : "—";
            String rateText = calculator.usesRate() ? FormatUtils.format(input.getRate()) : "—";
            String termText = calculator.usesTerm() ? FormatUtils.format(input.getTerm()) : "—";
            String extraText = buildExtraText(calculator, input);
            service.calculateAndAdd(calculator.getType(), input, amountText, rateText, termText, extraText);
            view.selectLastRow();
        } catch (ValidationException | CalculationException ex) {
            view.showError(ex.getMessage());
        }
    }

    private CalculationInput buildInput(Calculator calculator) throws ValidationException {
        CalculationInput.Builder builder = CalculationInput.builder();
        if (calculator.usesAmount()) {
            builder.amount(InputValidator.parsePositive(view.getAmountText(), "Сума інвестиції"));
        }
        if (calculator.usesRate()) {
            builder.rate(InputValidator.parseNonNegative(view.getRateText(), "Річна ставка"));
        }
        if (calculator.usesTerm()) {
            builder.term(InputValidator.parsePositive(view.getTermText(), "Термін"));
        }
        if (calculator.getExtraInputType() == ExtraInputType.VALUE) {
            double value = calculator.isExtraPositive()
                    ? InputValidator.parsePositive(view.getExtraText(), calculator.getExtraLabel())
                    : InputValidator.parseNumber(view.getExtraText(), calculator.getExtraLabel());
            builder.extraValue(value);
        } else if (calculator.getExtraInputType() == ExtraInputType.SERIES) {
            builder.series(InputValidator.parseSeries(view.getExtraText(), calculator.getExtraLabel()));
        }
        return builder.build();
    }

    private String buildExtraText(Calculator calculator, CalculationInput input) {
        if (calculator.getExtraInputType() == ExtraInputType.VALUE) {
            return FormatUtils.format(input.getExtraValue());
        }
        if (calculator.getExtraInputType() == ExtraInputType.SERIES) {
            return input.getSeries().stream()
                    .map(FormatUtils::format)
                    .collect(Collectors.joining("; "));
        }
        return "—";
    }
}
