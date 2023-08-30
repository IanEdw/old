package ru.aston.ian_ev.task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.ian_ev.task1.order.Discount;
import ru.aston.ian_ev.task1.order.Order;
import ru.aston.ian_ev.task1.order.digital.DigitalPhotoPrint;
import ru.aston.ian_ev.task1.order.film.Bundle;
import ru.aston.ian_ev.task1.order.film.FilmPhotoPrint;
import ru.aston.ian_ev.task1.user.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyOrderListTest {
    private static User userA;

    @BeforeAll
    static void initBeforeAll() {
        userA = new User(20, "Name", "A");
    }
    @Test
    void testSum() {
        Order order = new DigitalPhotoPrint(BigDecimal.ONE, BigDecimal.TEN, userA, 1);
        Order secondOrder = new FilmPhotoPrint(BigDecimal.TEN, BigDecimal.ONE, userA, Bundle.PREMIUM);

        DailyOrderList underTest = new DailyOrderList(List.of(order, secondOrder));

        assertEquals(0, underTest.calcDailyAmount().compareTo(BigDecimal.valueOf(20)));
    }

    @Test
    void testDiscount() {
        Discount discount = new DigitalPhotoPrint(BigDecimal.ONE, BigDecimal.TEN, userA, 0.8);
        Discount secondDiscount = new FilmPhotoPrint(BigDecimal.TEN, BigDecimal.ONE, userA, Bundle.COMMON);

        assertEquals(0, discount.getDiscount().compareTo(BigDecimal.valueOf(2)));
        assertEquals(0, secondDiscount.getDiscount().compareTo(BigDecimal.valueOf(5)));
    }

    @Test
    void testSumWithDiscount() {
        Order order = new DigitalPhotoPrint(BigDecimal.ONE, BigDecimal.TEN, userA, 0.8);
        Order secondOrder = new FilmPhotoPrint(BigDecimal.TEN, BigDecimal.ONE, userA, Bundle.COMMON);

        DailyOrderList underTest = new DailyOrderList(List.of(order, secondOrder));

        assertEquals(0, underTest.calcDailyAmount().compareTo(BigDecimal.valueOf(13)));
    }

    @Test
    void testAlphabeticalOrder() {
        User userB = new User(20, "NameB", "B");
        List<Order> orders = new ArrayList<>();
        orders.add(new FilmPhotoPrint(BigDecimal.TEN, BigDecimal.ONE, userB, Bundle.PREMIUM));
        orders.add(new DigitalPhotoPrint(BigDecimal.ONE, BigDecimal.TEN, userA, 1));

        assertEquals("B", orders.get(0).getUser().getSurname());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream out = System.out;
        System.setOut(ps);

        DailyOrderList underTest = new DailyOrderList(orders);
        underTest.printOrders();
        System.setOut(out);

        String firstOrder = baos.toString().split("\n")[0];
        int indexSurnameFirstUser = firstOrder.indexOf("surname='") + 9;

        assertEquals("A", firstOrder.substring(indexSurnameFirstUser, indexSurnameFirstUser + 1));
    }
}
