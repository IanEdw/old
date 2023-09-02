package ru.aston.ian_ev.task2;

import org.junit.jupiter.api.Test;
import ru.aston.ian_ev.task1.order.digital.DigitalPhotoPrint;
import ru.aston.ian_ev.task1.user.User;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadOrderArgumentsExceptionTest {
    @Test
    void orderWithUnitPriceLessThanOneExpectBadOrderArgumentsException() {
        assertThrows(
                BadOrderArgumentsException.class,
                () -> new DigitalPhotoPrint(
                        new BigDecimal(0),
                        new BigDecimal(1),
                        new User(20, "Name", "Surname"),
                        1
                )
        );
    }

    @Test
    void orderWithAmountLessThanOneExpectBadOrderArgumentsException() {
        assertThrows(
                BadOrderArgumentsException.class,
                () -> new DigitalPhotoPrint(
                        new BigDecimal(1),
                        new BigDecimal(0),
                        new User(20, "Name", "Surname"),
                        1
                )
        );
    }
}
