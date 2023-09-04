package ru.aston.ian_ev.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    private Map<String, String> map;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }
    
    @Test
    void constructorsTest() {
        map = new HashMap<>();
        map = new HashMap<>(20);
        map = new HashMap<>(20, 0.85F);
        map = new HashMap<>(Map.of("firstKey", "firstValue", "secondKey", "secondValue"));
    }

    @Test
    void putKeyValueExpectSizeEqualOne() {
        map.put("key", "value");
        assertEquals(1, map.size());
        assertTrue(map.containsKey("key"));
    }

    @Test
    void removeKeyExpectSizeEqualZero() {
        map.put("key", "value");
        assertEquals(1, map.size());
        assertTrue(map.containsKey("key"));

        map.remove("key");
        assertEquals(0, map.size());
        assertFalse(map.containsKey("key"));
    }

    @Test
    void getExpectValue() {
        map.put("key", "value");
        assertEquals("value", map.get("key"));
    }

    @Test
    void getOrDefaultExpectDefaultValue() {
        assertEquals(0, map.size());
        assertEquals("defaultValue", map.getOrDefault("key", "defaultValue"));
    }
}
