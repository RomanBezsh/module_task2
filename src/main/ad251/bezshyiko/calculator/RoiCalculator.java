package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ExtraInputType;

public class RoiCalculator implements Calculator {

    @Override
    public CalculationType getType() {
        return CalculationType.ROI;
    }

    @Override
    public String getResultLabel() {
        return "Рентабельність інвестицій";
    }

    @Override
    public String getResultUnit() {
        return "%";
    }

    @Override
    public String getHint() {
        return "Введіть суму інвестиції та чистий прибуток.";
    }

    @Override
    public boolean usesRate() {
        return false;
    }

    @Override
    public boolean usesTerm() {
        return false;
    }

    @Override
    public ExtraInputType getExtraInputType() {
        return ExtraInputType.VALUE;
    }

    @Override
    public String getExtraLabel() {
        return "Чистий прибуток, грн";
    }

    @Override
    public double calculate(CalculationInput input) throws CalculationException {
        return MathUtils.divide(input.getExtraValue(), input.getAmount(), "Сума інвестиції") * 100.0;
    }
}
