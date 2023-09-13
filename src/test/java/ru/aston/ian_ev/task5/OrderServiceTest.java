package ru.aston.ian_ev.task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.ian_ev.task4.dao.OrderDAO;
import ru.aston.ian_ev.task4.models.Order;
import ru.aston.ian_ev.task5.service.OrderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderDAO orderDAO;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderDAO = mock(OrderDAO.class);
        orderService = new OrderService(orderDAO);
    }

    @Test
    void getOrderExpectOnceCallFindOrder() {
        when(orderDAO.findEntityById(anyInt())).thenReturn(new Order());

        Order order = orderService.getOrderById(1);

        assertNotNull(order);
        verify(orderDAO, atLeastOnce()).findEntityById(anyInt());
    }

    @Test
    void getAllOrdersExpectReturnOrders() {
        when(orderDAO.findAll()).thenReturn(List.of(new Order(), new Order()));

        List<Order> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(orderDAO, atLeastOnce()).findAll();
    }

    @Test
    void createOrderExpectCallCreateOrderOnce() {
        when(orderDAO.create(any())).thenReturn(true);

        boolean val = orderService.createOrder(new Order());

        assertTrue(val);
        verify(orderDAO, atLeastOnce()).create(any());
    }

    @Test
    void deleteOrderByIdExpectCallDelete() {
        when(orderDAO.delete(anyInt())).thenReturn(true);

        boolean val = orderService.deleteOrderById(1);

        assertTrue(val);
        verify(orderDAO, atLeastOnce()).delete(anyInt());
    }

    @Test
    void updateOrderExpectCallUpdateOrder() {
        when(orderDAO.update(any())).thenReturn(new Order());

        Order order = orderService.updateOrder(new Order());

        assertNotNull(order);
        verify(orderDAO, atLeastOnce()).update(any());
    }
}
