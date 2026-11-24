package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;

public final class MathUtils {

    public static double divide(double dividend, double divisor, String fieldName) throws CalculationException {
        if (divisor == 0.0) {
            throw new CalculationException("Ділення на нуль: «" + fieldName + "» не може дорівнювати нулю.");
        }
        return dividend / divisor;
    }

    public static double squareRoot(double value, String fieldName) throws CalculationException {
        if (value < 0.0) {
            throw new CalculationException("Неможливо обчислити корінь з від'ємного числа: «" + fieldName + "».");
        }
        return Math.sqrt(value);
    }
}
