package ad251.bezshyiko.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Repository")
class RepositoryTest {

    @Test
    void returnsUnmodifiableSnapshot() {
        Repository<String> repository = new Repository<>();
        repository.add("Java");
        repository.add("Maven");

        List<String> snapshot = repository.getAll();

        assertEquals(List.of("Java", "Maven"), snapshot);
        assertThrows(UnsupportedOperationException.class, () -> snapshot.add("JUnit"));
    }

    @Test
    void acceptsPredicateOfSupertype() {
        Repository<Number> repository = new Repository<>();
        repository.add(1);
        repository.add(-2);
        repository.add(3.5);

        Predicate<Object> positive = value -> ((Number) value).doubleValue() > 0;
        List<Number> result = repository.findAll(positive);

        assertEquals(2, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(3.5));
    }

    @Test
    void removesMatchingItemsAndReportsCount() {
        Repository<Integer> repository = new Repository<>();
        repository.add(1);
        repository.add(2);
        repository.add(3);

        int removed = repository.removeIf(value -> value < 3);

        assertEquals(2, removed);
        assertEquals(List.of(3), repository.getAll());
    }
}
