package ad251.bezshyiko.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("CalculationService singleton")
class CalculationServiceSingletonTest {

    @Test
    void returnsSameInstance() {
        assertSame(CalculationService.getInstance(), CalculationService.getInstance());
    }
}
