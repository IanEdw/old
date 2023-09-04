package ru.aston.ian_ev.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {
    private TreeSet<Foo> set;

    @BeforeEach
    void setUp() {
        set = new TreeSet<>();
    }

    @Test
    void constructorsTest() {
        set = new TreeSet<>();
        set = new TreeSet<>((f1, f2) -> f1.getValue() - f2.getValue());
        set = new TreeSet<>(new ArrayList<>());
        set = new TreeSet<>(new HashSet<>());
        set = new TreeSet<>(new TreeSet<>());
    }

    @Test
    void addFooExpectSetContainsFoo() {
        Foo foo = new Foo(1);
        assertFalse(set.contains(foo));

        set.add(foo);
        assertTrue(set.contains(foo));
    }

    @Test
    void addAllExpectAllInSet() {
        List<Foo> list = List.of(new Foo(1), new Foo(2));
        set.addAll(list);
        set.add(new Foo(3));

        assertTrue(set.containsAll(list));
    }

    @Test
    void removeFooExpectEmptySet() {
        Foo foo = new Foo(1);
        set.add(foo);
        set.remove(foo);
        assertTrue(set.isEmpty());
    }

    @Test
    void removeAllExpectEmptySet() {
        List<Foo> list = List.of(new Foo(1), new Foo(2));
        set.addAll(list);
        set.removeAll(list);
        assertTrue(set.isEmpty());
    }

    @Test
    void pollFirstExpectPollFooWithValueOne() {
        set.addAll(List.of(new Foo(3), new Foo(2), new Foo(1)));
        Foo first = set.pollFirst();

        assertEquals(1, first.getValue());
        assertFalse(set.contains(first));
    }

    @Test
    void pollLastExpectPollFooWithValueThree() {
        set.addAll(List.of(new Foo(3), new Foo(2), new Foo(1)));
        Foo last = set.pollLast();

        assertEquals(3, last.getValue());
        assertFalse(set.contains(last));
    }
}

class Foo implements Comparable<Foo> {
    private int value;

    public Foo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Foo o) {
        return value - o.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foo foo = (Foo) o;

        return value == foo.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
