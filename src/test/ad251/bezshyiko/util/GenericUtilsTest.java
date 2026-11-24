package ad251.bezshyiko.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("GenericUtils")
class GenericUtilsTest {

    @Test
    void copiesSubtypeElementsIntoSupertypeCollection() {
        List<Integer> source = List.of(1, 2, 3);
        List<Number> destination = new ArrayList<>();

        GenericUtils.copyAll(source, destination);

        assertEquals(List.of(1, 2, 3), destination);
    }

    @Test
    void findsMaximumElement() {
        assertEquals(9, GenericUtils.max(List.of(3, 9, 1)));
        assertEquals("Maven", GenericUtils.max(List.of("Java", "Maven", "JUnit")));
    }

    @Test
    void rejectsEmptyCollection() {
        assertThrows(IllegalArgumentException.class, () -> GenericUtils.max(List.of()));
    }
}
