package ru.aston.ian_ev.task1.order;

import ru.aston.ian_ev.task1.user.User;
import ru.aston.ian_ev.task2.BadOrderArgumentsException;
import ru.aston.ian_ev.task2.ErrorCode;

import java.math.BigDecimal;

public abstract class Order implements Discount, Comparable<Order> {
    protected BigDecimal unitPrice;
    protected BigDecimal amount;
    protected User user;

    public Order(BigDecimal unitPrice, BigDecimal amount, User user) throws BadOrderArgumentsException {
        if (unitPrice.compareTo(BigDecimal.ONE) < 0) {
            throw new BadOrderArgumentsException("Минимально возможная цена - 1", ErrorCode.INVALID_UNIT_PRICE);
        }
        if (amount.compareTo(BigDecimal.ONE) < 0) {
            throw new BadOrderArgumentsException("Минимально возможное количество - 1", ErrorCode.INVALID_AMOUNT);
        }
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.user = user;
    }

    public abstract BigDecimal getPrice();

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return user.getSurname().compareTo(o.getUser().getSurname());
    }
}
