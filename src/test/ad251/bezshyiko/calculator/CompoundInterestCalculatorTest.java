package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CompoundInterestCalculator")
class CompoundInterestCalculatorTest {

    private final CompoundInterestCalculator calculator = new CompoundInterestCalculator();

    @Test
    void calculatesCompoundFutureValue() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(10.0)
                .term(2.0)
                .build();
        assertEquals(1210.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void calculatesWithFractionalRate() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(5.5)
                .term(3.0)
                .build();
        assertEquals(1174.24, calculator.calculate(input), 0.01);
    }
}
