package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;

public class AnnuityCalculator implements Calculator {

    @Override
    public CalculationType getType() {
        return CalculationType.ANNUITY_PAYMENT;
    }

    @Override
    public String getResultLabel() {
        return "Щомісячний платіж";
    }

    @Override
    public String getResultUnit() {
        return "грн/міс";
    }

    @Override
    public String getHint() {
        return "Введіть суму кредиту, річну ставку та термін у роках.";
    }

    @Override
    public double calculate(CalculationInput input) throws CalculationException {
        double months = input.getTerm() * 12.0;
        double monthlyRate = input.getRate() / 100.0 / 12.0;
        if (monthlyRate == 0.0) {
            return MathUtils.divide(input.getAmount(), months, "Кількість місяців");
        }
        double denominator = 1.0 - 1.0 / Math.pow(1.0 + monthlyRate, months);
        return MathUtils.divide(input.getAmount() * monthlyRate, denominator, "Знаменник ануїтету");
    }
}
