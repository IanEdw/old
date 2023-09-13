package ru.aston.ian_ev.task5.service;

import ru.aston.ian_ev.task4.dao.OrderDAO;
import ru.aston.ian_ev.task4.models.Order;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    public Order getOrderById(int id) {
        return orderDAO.findEntityById(id);
    }

    public boolean deleteOrderById(int id) {
        return orderDAO.delete(id);
    }

    public boolean createOrder(Order order) {
        return orderDAO.create(order);
    }

    public Order updateOrder(Order order) {
        return orderDAO.update(order);
    }
}
