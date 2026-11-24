package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("RiskCalculator")
class RiskCalculatorTest {

    private final RiskCalculator calculator = new RiskCalculator();

    @Test
    void calculatesStandardDeviation() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .series(List.of(10.0, 12.0, 14.0))
                .build();
        assertEquals(1.632993, calculator.calculate(input), 0.000001);
    }

    @Test
    void singleValueHasZeroRisk() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .series(List.of(10.0))
                .build();
        assertEquals(0.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void rejectsEmptySeries() {
        CalculationInput input = CalculationInput.builder().build();
        assertThrows(CalculationException.class, () -> calculator.calculate(input));
    }
}
