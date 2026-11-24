package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("PaybackCalculator")
class PaybackCalculatorTest {

    private final PaybackCalculator calculator = new PaybackCalculator();

    @Test
    void calculatesPaybackPeriod() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(10000.0)
                .extraValue(2500.0)
                .build();
        assertEquals(4.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void rejectsZeroIncome() {
        CalculationInput input = CalculationInput.builder()
                .amount(10000.0)
                .extraValue(0.0)
                .build();
        assertThrows(CalculationException.class, () -> calculator.calculate(input));
    }

    @Test
    void requiresPositiveIncome() {
        assertTrue(calculator.isExtraPositive());
    }
}
