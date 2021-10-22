import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayListImplTest {

    @Test
    void testCreate() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testCreateWithInitialCapacity() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>(100);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testCreateWithCollection() {
        ArrayListImpl<Boolean> list = new ArrayListImpl<>(Arrays.asList(true, false, true));
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void testCreateWithZeroSizeCollection() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>(Arrays.asList());
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testCreateWithIllegalCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayListImpl<>(-1));
    }

    @Test
    void testGet() {
        ArrayListImpl<String> list = new ArrayListImpl<>(Arrays.asList("One", "Two", "Three"));
        Assertions.assertEquals("Two", list.get(1));
    }

    @Test
    void testGetWithIndexOutOfBound() {
        ArrayListImpl<String> list = new ArrayListImpl<>(Arrays.asList("One", "Two", "Three"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testAdd() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>(0);
        Assertions.assertTrue(list.add(1));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    void testAddWithGrow() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>(2);
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        Assertions.assertEquals(50, list.size());
    }

    @Test
    void testAddNull() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        Assertions.assertTrue(list.add(null));
        Assertions.assertTrue(list.add(null));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void testRemove() {
        ArrayListImpl<String> list = new ArrayListImpl<>(Arrays.asList("One", "Two", "Three"));
        Assertions.assertEquals("Two", list.remove(1));
        Assertions.assertEquals("Three", list.remove(1));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals("One", list.get(0));
    }

    @Test
    void testRemoveWithIndexOutOfBound() {
        ArrayListImpl<String> list = new ArrayListImpl<>(Arrays.asList("One", "Two", "Three"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(100));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void testToString() {
        ArrayListImpl<String> list = new ArrayListImpl<>(Arrays.asList("One", "Two", "Three"));
        Assertions.assertEquals("[One, Two, Three]", list.toString());
    }

    @Test
    void testToStringEmpty() {
        ArrayListImpl<String> list = new ArrayListImpl<>();
        Assertions.assertEquals("[]", list.toString());
    }

    @Test
    void testQuickSort() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>(Arrays.asList(8, 4, 1, 7, 0, 9, 2, 6, 4, 3, 5));
        list.quickSort(Integer::compareTo);
        Assertions.assertEquals("[0, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9]", list.toString());
    }

    @Test
    void testQuickSortWithNull() {
        ArrayListImpl<Character> list = new ArrayListImpl<>(Arrays.asList('B', 'F', null, 'A', 'C', 'E', 'G', 'D', null));
        list.quickSort(Comparator.nullsFirst(Character::compareTo));
        Assertions.assertEquals("[null, null, A, B, C, D, E, F, G]", list.toString());
    }

    @Test
    void testQuickSortEmptyList() {
        ArrayListImpl<Character> list = new ArrayListImpl<>(Arrays.asList());
        list.quickSort(Character::compareTo);
        Assertions.assertEquals("[]", list.toString());
    }

    @Test
    void testQuickSortSortedList() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        list.quickSort(Integer::compareTo);
        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(i, list.get(i));
        }
    }
}