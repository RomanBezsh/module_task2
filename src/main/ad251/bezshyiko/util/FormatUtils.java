package ad251.bezshyiko.util;

import java.util.Locale;

public final class FormatUtils {

    public static String format(double value) {
        return String.format(Locale.US, "%.2f", value);
    }
}
