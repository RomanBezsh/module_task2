package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;

public class SimpleInterestCalculator implements Calculator {

    @Override
    public CalculationType getType() {
        return CalculationType.SIMPLE_INTEREST;
    }

    @Override
    public String getResultLabel() {
        return "Майбутня вартість";
    }

    @Override
    public String getResultUnit() {
        return "грн";
    }

    @Override
    public String getHint() {
        return "Введіть початкову суму, річну ставку та термін у роках.";
    }

    @Override
    public double calculate(CalculationInput input) {
        return input.getAmount() * (1.0 + input.getRate() / 100.0 * input.getTerm());
    }
}
