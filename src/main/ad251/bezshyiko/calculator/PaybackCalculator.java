package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ExtraInputType;

public class PaybackCalculator implements Calculator {

    @Override
    public CalculationType getType() {
        return CalculationType.PAYBACK_PERIOD;
    }

    @Override
    public String getResultLabel() {
        return "Термін окупності";
    }

    @Override
    public String getResultUnit() {
        return "років";
    }

    @Override
    public String getHint() {
        return "Введіть суму інвестиції та річний дохід.";
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
        return "Річний дохід, грн";
    }

    @Override
    public boolean isExtraPositive() {
        return true;
    }

    @Override
    public double calculate(CalculationInput input) throws CalculationException {
        return MathUtils.divide(input.getAmount(), input.getExtraValue(), "Річний дохід");
    }
}
