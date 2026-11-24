package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ExtraInputType;

import java.util.List;

public class RiskCalculator implements Calculator {

    @Override
    public CalculationType getType() {
        return CalculationType.RISK;
    }

    @Override
    public String getResultLabel() {
        return "Середньоквадратичне відхилення";
    }

    @Override
    public String getResultUnit() {
        return "%";
    }

    @Override
    public String getHint() {
        return "Введіть дохідності через крапку з комою, наприклад: 10; 12,5; -3.";
    }

    @Override
    public boolean usesAmount() {
        return false;
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
        return ExtraInputType.SERIES;
    }

    @Override
    public String getExtraLabel() {
        return "Дохідності, %";
    }

    @Override
    public String getExtraHint() {
        return "Значення розділяйте крапкою з комою";
    }

    @Override
    public double calculate(CalculationInput input) throws CalculationException {
        List<Double> values = input.getSeries();
        if (values.isEmpty()) {
            throw new CalculationException("Введіть хоча б одне значення дохідності.");
        }
        double mean = 0.0;
        for (double value : values) {
            mean += value;
        }
        mean /= values.size();
        double sum = 0.0;
        for (double value : values) {
            double deviation = value - mean;
            sum += deviation * deviation;
        }
        double variance = sum / values.size();
        return MathUtils.squareRoot(variance, "Дисперсія");
    }
}
