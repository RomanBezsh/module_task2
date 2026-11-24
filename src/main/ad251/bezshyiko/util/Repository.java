package ad251.bezshyiko.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Repository<T> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(Objects.requireNonNull(item, "item"));
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public int removeIf(Predicate<? super T> condition) {
        int before = items.size();
        items.removeIf(condition);
        return before - items.size();
    }

    public List<T> findAll(Predicate<? super T> condition) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (condition.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public T get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }
}
