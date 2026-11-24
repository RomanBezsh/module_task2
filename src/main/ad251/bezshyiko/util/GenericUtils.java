package ad251.bezshyiko.util;

import java.util.List;

public final class GenericUtils {

    public static <T> void copyAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T extends Comparable<? super T>> T max(List<? extends T> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Collection must not be empty");
        }
        T maximum = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            T current = items.get(i);
            if (current.compareTo(maximum) > 0) {
                maximum = current;
            }
        }
        return maximum;
    }
}
