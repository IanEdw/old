package ru.aston.ian_ev.task1;

import ru.aston.ian_ev.task1.order.Order;

import java.math.BigDecimal;
import java.util.List;

public class DailyOrderList implements OrderCalculator {
    private final List<Order> orders;
    private BigDecimal sum;

    public DailyOrderList(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public BigDecimal calcDailyAmount() {
        if (sum == null) {
            sum = orders.stream().map(Order::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return sum;
    }

    public void printOrders() {
        orders.sort(Order::compareTo);
        orders.forEach(System.out::println);
    }
}
