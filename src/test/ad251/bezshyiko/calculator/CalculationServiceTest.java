package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ResultRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CalculationService")
class CalculationServiceTest {

    private final CalculationService service = new CalculationService();

    @Test
    void calculatesAndAddsRecord() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(10.0)
                .term(2.0)
                .build();
        ResultRecord record = service.calculateAndAdd(CalculationType.SIMPLE_INTEREST, input,
                "1000.00", "10.00", "2.00", "—");

        assertEquals(1, record.getNumber());
        assertEquals("1200.00 грн", record.getResultText());
        assertEquals(1200.0, record.getResultValue(), 0.000001);
        assertEquals(1, service.getRecords().size());
    }

    @Test
    void incrementsRecordNumbers() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(10.0)
                .term(2.0)
                .build();
        service.calculateAndAdd(CalculationType.SIMPLE_INTEREST, input, "1000.00", "10.00", "2.00", "—");
        ResultRecord second = service.calculateAndAdd(CalculationType.COMPOUND_INTEREST, input,
                "1000.00", "10.00", "2.00", "—");

        assertEquals(2, second.getNumber());
        assertEquals(2, service.getRecords().size());
    }

    @Test
    void clearsRecords() throws Exception {
        CalculationInput input = CalculationInput.builder()
                .amount(1000.0)
                .rate(10.0)
                .term(2.0)
                .build();
        service.calculateAndAdd(CalculationType.SIMPLE_INTEREST, input, "1000.00", "10.00", "2.00", "—");
        service.clear();

        assertTrue(service.getRecords().isEmpty());

        ResultRecord record = service.calculateAndAdd(CalculationType.SIMPLE_INTEREST, input,
                "1000.00", "10.00", "2.00", "—");
        assertEquals(1, record.getNumber());
    }
}
