package ad251.bezshyiko.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class InputValidator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("[+-]?\\d+([.,]\\d+)?");

    public static double parseNumber(String text, String fieldName) throws ValidationException {
        if (text == null || text.trim().isEmpty()) {
            throw new ValidationException("Поле «" + fieldName + "» не може бути порожнім.");
        }
        String normalized = text.trim().replace(',', '.');
        if (!NUMBER_PATTERN.matcher(normalized).matches()) {
            throw new ValidationException("Поле «" + fieldName + "» повинно містити число.");
        }
        double value = Double.parseDouble(normalized);
        if (!Double.isFinite(value)) {
            throw new ValidationException("Поле «" + fieldName + "» містить недопустиме число.");
        }
        return value;
    }

    public static double parsePositive(String text, String fieldName) throws ValidationException {
        double value = parseNumber(text, fieldName);
        if (value <= 0) {
            throw new ValidationException("Поле «" + fieldName + "» повинно бути більше нуля.");
        }
        return value;
    }

    public static double parseNonNegative(String text, String fieldName) throws ValidationException {
        double value = parseNumber(text, fieldName);
        if (value < 0) {
            throw new ValidationException("Поле «" + fieldName + "» не може бути від'ємним.");
        }
        return value;
    }

    public static List<Double> parseSeries(String text, String fieldName) throws ValidationException {
        if (text == null || text.trim().isEmpty()) {
            throw new ValidationException("Поле «" + fieldName + "» не може бути порожнім.");
        }
        String[] parts = text.trim().split("[;\\s]+");
        List<Double> values = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            values.add(parseNumber(parts[i], fieldName + " №" + (i + 1)));
        }
        return values;
    }
}
