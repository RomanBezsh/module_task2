package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ResultRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CalculationService collections")
class CalculationServiceCollectionsTest {

    private CalculationService service;

    @BeforeEach
    void setUp() {
        service = new CalculationService();
    }

    private ResultRecord add(CalculationType type, String amount, String rate, String term) throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(Double.parseDouble(amount))
                .rate(10.0)
                .term(2.0)
                .build();
        return service.calculateAndAdd(type, input, amount, rate, term, "-");
    }

    @Test
    void groupsRecordsByType() throws Exception {
        add(CalculationType.SIMPLE_INTEREST, "1000.00", "10.00", "2.00");
        add(CalculationType.COMPOUND_INTEREST, "1000.00", "10.00", "2.00");
        add(CalculationType.SIMPLE_INTEREST, "2000.00", "10.00", "2.00");

        Map<CalculationType, List<ResultRecord>> groups = service.groupByType();

        assertEquals(2, groups.size());
        assertEquals(2, groups.get(CalculationType.SIMPLE_INTEREST).size());
        assertEquals(1, groups.get(CalculationType.COMPOUND_INTEREST).size());
    }

    @Test
    void returnsUsedTypesAsSet() throws Exception {
        add(CalculationType.SIMPLE_INTEREST, "1000.00", "10.00", "2.00");
        add(CalculationType.SIMPLE_INTEREST, "2000.00", "10.00", "2.00");
        add(CalculationType.ROI, "1000.00", "-", "-");

        Set<CalculationType> types = service.getUsedTypes();

        assertEquals(2, types.size());
        assertTrue(types.contains(CalculationType.SIMPLE_INTEREST));
        assertTrue(types.contains(CalculationType.ROI));
    }

    @Test
    void sortsRecordsByResultDescending() throws Exception {
        add(CalculationType.SIMPLE_INTEREST, "1000.00", "10.00", "2.00");
        add(CalculationType.SIMPLE_INTEREST, "5000.00", "10.00", "2.00");
        add(CalculationType.SIMPLE_INTEREST, "2000.00", "10.00", "2.00");

        List<ResultRecord> sorted = service.getRecordsSortedByResultDescending();

        assertEquals(6000.0, sorted.get(0).getResultValue(), 0.000001);
        assertEquals(2400.0, sorted.get(1).getResultValue(), 0.000001);
        assertEquals(1200.0, sorted.get(2).getResultValue(), 0.000001);
    }

    @Test
    void removesRecordsBelowThreshold() throws Exception {
        add(CalculationType.SIMPLE_INTEREST, "1000.00", "10.00", "2.00");
        add(CalculationType.SIMPLE_INTEREST, "5000.00", "10.00", "2.00");

        int removed = service.removeRecordsBelow(2000.0);

        assertEquals(1, removed);
        assertEquals(1, service.getRecords().size());
        assertEquals(6000.0, service.getRecords().get(0).getResultValue(), 0.000001);
    }
}
