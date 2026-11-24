package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CalculatorRegistry")
class CalculatorRegistryTest {

    private final CalculatorRegistry registry = new CalculatorRegistry();

    @Test
    void containsAllCalculationTypes() {
        assertEquals(CalculationType.values().length, registry.getFactories().size());
        for (CalculationType type : CalculationType.values()) {
            assertEquals(type, registry.get(type).getType());
        }
    }

    @Test
    void rejectsUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> registry.get(null));
    }
}
