package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SimpleInterestCalculator")
class SimpleInterestCalculatorTest {

    private final SimpleInterestCalculator calculator = new SimpleInterestCalculator();

    @Test
    void calculatesFutureValue() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(10.0)
                .term(2.0)
                .build();
        assertEquals(1200.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void returnsSameAmountWithZeroRate() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(0.0)
                .term(5.0)
                .build();
        assertEquals(1000.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void exposesMetadata() {
        assertEquals(CalculationType.SIMPLE_INTEREST, calculator.getType());
        assertEquals("грн", calculator.getResultUnit());
        assertTrue(calculator.usesAmount());
        assertTrue(calculator.usesRate());
        assertTrue(calculator.usesTerm());
    }
}
