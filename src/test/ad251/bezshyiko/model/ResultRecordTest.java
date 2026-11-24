package ad251.bezshyiko.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ResultRecord")
class ResultRecordTest {

    private ResultRecord record(int number, double resultValue) {
        return ResultRecord.builder()
                .number(number)
                .type(CalculationType.SIMPLE_INTEREST)
                .amount("1000.00")
                .rate("10.00")
                .term("2.00")
                .extra("-")
                .resultValue(resultValue)
                .resultText("1200.00 UAH")
                .build();
    }

    @Test
    void equalsAndHashCodeUseAllFields() {
        ResultRecord first = record(1, 1200.0);
        ResultRecord second = record(1, 1200.0);
        ResultRecord different = record(2, 1200.0);

        assertAll(
                () -> assertEquals(first, second),
                () -> assertEquals(first.hashCode(), second.hashCode()),
                () -> assertTrue(!first.equals(different))
        );
    }

    @Test
    void comparesByNumber() {
        assertTrue(record(1, 1200.0).compareTo(record(2, 1200.0)) < 0);
    }

    @Test
    void toStringContainsTypeAndValue() {
        String text = record(1, 1200.0).toString();
        assertAll(
                () -> assertTrue(text.contains("SIMPLE_INTEREST")),
                () -> assertTrue(text.contains("1200.0"))
        );
    }
}
