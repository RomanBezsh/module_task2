package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ExtraInputType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("RoiCalculator")
class RoiCalculatorTest {

    private final RoiCalculator calculator = new RoiCalculator();

    @Test
    void calculatesRoi() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .extraValue(250.0)
                .build();
        assertEquals(25.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void calculatesNegativeRoi() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .extraValue(-100.0)
                .build();
        assertEquals(-10.0, calculator.calculate(input), 0.000001);
    }

    @Test
    void rejectsZeroInvestment() {
        CalculationInput input = CalculationInput.builder()
                .amount(0.0)
                .extraValue(250.0)
                .build();
        assertThrows(CalculationException.class, () -> calculator.calculate(input));
    }

    @Test
    void exposesMetadata() {
        assertEquals(CalculationType.ROI, calculator.getType());
        assertEquals(ExtraInputType.VALUE, calculator.getExtraInputType());
        assertFalse(calculator.usesRate());
        assertFalse(calculator.usesTerm());
    }
}
