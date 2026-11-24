package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ExtraInputType;

public interface Calculator {

    CalculationType getType();

    String getResultLabel();

    String getResultUnit();

    String getHint();

    default boolean usesAmount() {
        return true;
    }

    default boolean usesRate() {
        return true;
    }

    default boolean usesTerm() {
        return true;
    }

    default ExtraInputType getExtraInputType() {
        return ExtraInputType.NONE;
    }

    default String getExtraLabel() {
        return "Додатковий параметр";
    }

    default String getExtraHint() {
        return "";
    }

    default boolean isExtraPositive() {
        return false;
    }

    double calculate(CalculationInput input) throws CalculationException;
}
