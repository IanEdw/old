package ru.aston.ian_ev.task4;

import org.junit.jupiter.api.*;
import ru.aston.ian_ev.task4.dao.OrderDAO;
import ru.aston.ian_ev.task4.models.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOTest {
    private static OrderDAO underTest;

    @BeforeAll
    static void beforeAll() {
        underTest = new OrderDAO();
    }

    @BeforeEach
    void setUp() {
        Utils.executeSqlScripts("sql/schema.sql");
        Utils.executeSqlScripts("sql/data.sql");
    }

    @AfterEach
    void tearDown() {
        Utils.executeSqlScripts("sql/drop.sql");
    }

    @Test
    void findAllExpectFourOrders() {
        List<Order> orders = underTest.findAll();

        assertEquals(4, orders.size());
    }

    @Test
    void findEntityByIdExpectOrder() {
        Order order = underTest.findEntityById(1);

        assertEquals(1, order.getUserId());
        assertEquals(1, order.getAmount());
    }

    @Test
    void createExpectOrderAddInDatabase() {
        Order order = new Order();
        order.setAmount(10);
        order.setUserId(2);

        assertTrue(underTest.create(order));

        order = underTest.findEntityById(5);
        assertEquals(10, order.getAmount());
        assertEquals(2, order.getUserId());
    }

    @Test
    void updateOrderExpectUpdatedOrder() {
        Order order = underTest.findEntityById(1);
        order.setAmount(10);
        order.setUserId(2);

        underTest.update(order);

        order = underTest.findEntityById(1);

        assertEquals(10, order.getAmount());
        assertEquals(2, order.getUserId());
    }

    @Test
    void deleteByIdExpectDelete() {
        assertTrue(underTest.delete(1));
        assertThrows(RuntimeException.class, () -> underTest.findEntityById(1));
    }
}
