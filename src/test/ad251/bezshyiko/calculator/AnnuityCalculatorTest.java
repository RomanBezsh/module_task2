package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("AnnuityCalculator")
class AnnuityCalculatorTest {

    private final AnnuityCalculator calculator = new AnnuityCalculator();

    @Test
    void calculatesMonthlyPayment() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(10000.0)
                .rate(12.0)
                .term(1.0)
                .build();
        assertEquals(888.49, calculator.calculate(input), 0.01);
    }

    @Test
    void handlesZeroRate() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(12000.0)
                .rate(0.0)
                .term(1.0)
                .build();
        assertEquals(1000.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void rejectsZeroTerm() {
        CalculationInput input = CalculationInput.builder()
                .amount(10000.0)
                .rate(12.0)
                .term(0.0)
                .build();
        assertThrows(CalculationException.class, () -> calculator.calculate(input));
    }
}
