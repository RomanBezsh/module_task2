package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("MathUtils")
class MathUtilsTest {

    private static final double DELTA = 0.000001;

    @Test
    void dividesNumbers() throws CalculationException {
        assertEquals(2.5, MathUtils.divide(5.0, 2.0, "Дільник"), DELTA);
    }

    @Test
    void rejectsDivisionByZero() {
        assertThrows(CalculationException.class,
                () -> MathUtils.divide(5.0, 0.0, "Дільник"));
    }

    @Test
    void calculatesSquareRoot() throws CalculationException {
        assertEquals(3.0, MathUtils.squareRoot(9.0, "Дисперсія"), DELTA);
    }

    @Test
    void rejectsSquareRootOfNegativeNumber() {
        assertThrows(CalculationException.class,
                () -> MathUtils.squareRoot(-1.0, "Дисперсія"));
    }
}
