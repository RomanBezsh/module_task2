package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CalculationService observer")
class CalculationServiceObserverTest {

    @Test
    void notifiesListenerWhenRecordsChange() throws Exception {
        CalculationService service = new CalculationService();
        List<Integer> sizes = new ArrayList<>();
        service.addListener(records -> sizes.add(records.size()));

        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(10.0)
                .term(2.0)
                .build();

        service.calculateAndAdd(CalculationType.SIMPLE_INTEREST, input,
                "1000.00", "10.00", "2.00", "—");
        service.clear();

        assertEquals(List.of(1, 0), sizes);
    }

    @Test
    void stopsNotifyingRemovedListener() throws Exception {
        CalculationService service = new CalculationService();
        List<Integer> sizes = new ArrayList<>();
        CalculationListener listener = records -> sizes.add(records.size());
        service.addListener(listener);
        service.removeListener(listener);

        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(10.0)
                .term(2.0)
                .build();

        service.calculateAndAdd(CalculationType.SIMPLE_INTEREST, input,
                "1000.00", "10.00", "2.00", "—");

        assertEquals(List.of(), sizes);
    }
}
