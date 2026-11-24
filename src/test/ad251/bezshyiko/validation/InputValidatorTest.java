package ad251.bezshyiko.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("InputValidator")
class InputValidatorTest {

    private static final double DELTA = 0.000001;

    @Test
    void parsesDotDecimal() throws ValidationException {
        assertEquals(12.5, InputValidator.parseNumber("12.5", "Значення"), DELTA);
    }

    @Test
    void parsesCommaDecimal() throws ValidationException {
        assertEquals(12.5, InputValidator.parseNumber("12,5", "Значення"), DELTA);
    }

    @Test
    void parsesNegativeNumberWhenAllowed() throws ValidationException {
        assertEquals(-3.0, InputValidator.parseNumber("-3", "Значення"), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {"12.5|12.5", "12,5|12.5", "-3|-3"}, delimiter = '|')
    void parsesSupportedNumberFormats(String text, double expected) throws ValidationException {
        assertEquals(expected, InputValidator.parseNumber(text, "Amount"), DELTA);
    }

    @Test
    void rejectsLetters() {
        assertThrows(ValidationException.class,
                () -> InputValidator.parseNumber("12a", "Значення"));
    }

    @Test
    void rejectsEmptyValue() {
        assertThrows(ValidationException.class,
                () -> InputValidator.parseNumber("   ", "Значення"));
    }

    @Test
    void rejectsZeroForPositiveField() {
        assertThrows(ValidationException.class,
                () -> InputValidator.parsePositive("0", "Сума"));
    }

    @Test
    void rejectsNegativeForPositiveField() {
        assertThrows(ValidationException.class,
                () -> InputValidator.parsePositive("-10", "Сума"));
    }

    @Test
    void acceptsZeroForNonNegativeField() throws ValidationException {
        assertEquals(0.0, InputValidator.parseNonNegative("0", "Ставка"), DELTA);
    }

    @Test
    void rejectsNegativeForNonNegativeField() {
        assertThrows(ValidationException.class,
                () -> InputValidator.parseNonNegative("-0.5", "Ставка"));
    }

    @Test
    void parsesSeriesWithSemicolons() throws ValidationException {
        List<Double> values = InputValidator.parseSeries("10; 12,5; -3", "Дохідності");
        assertEquals(3, values.size());
        assertEquals(10.0, values.get(0), DELTA);
        assertEquals(12.5, values.get(1), DELTA);
        assertEquals(-3.0, values.get(2), DELTA);
    }

    @Test
    void rejectsEmptySeries() {
        assertThrows(ValidationException.class,
                () -> InputValidator.parseSeries(" ", "Дохідності"));
    }

    @Test
    void rejectsSeriesWithInvalidItem() {
        assertThrows(ValidationException.class,
                () -> InputValidator.parseSeries("10; abc; 12", "Дохідності"));
    }
}
