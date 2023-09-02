package ru.aston.ian_ev.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTest {
    private List<String> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    void constructorsTest() {
        list = new ArrayList<>();
        list = new ArrayList<>(20);
        list = new ArrayList<>(List.of("first", "second"));
    }

    @Test
    void addStringExpectSizeEqualOne() {
        assertTrue(list.add("first"));
        assertEquals("first", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void removeStringExpectSizeEqualZero() {
        String first = "first";
        list.add(first);
        assertEquals(1, list.size());

        list.remove(first);
        assertEquals(0, list.size());
    }

    @Test
    void addAllExpectSizeEqualTwo() {
        list.addAll(List.of("first", "second"));
        assertEquals(2, list.size());
    }

    @Test
    void sortExpectAlphabeticalOrder() {
        list.addAll(List.of("B", "C", "A"));
        assertEquals("B", list.get(0));

        Collections.sort(list, String::compareTo);

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }
}
