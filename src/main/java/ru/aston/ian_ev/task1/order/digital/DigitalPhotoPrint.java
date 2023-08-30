package ru.aston.ian_ev.task1.order.digital;

import ru.aston.ian_ev.task1.order.Order;
import ru.aston.ian_ev.task1.user.User;

import java.math.BigDecimal;

public class DigitalPhotoPrint extends Order {
    private final double imageQuality;
    public DigitalPhotoPrint(BigDecimal unitPrice, BigDecimal amount, User user, double imageQuality) {
        super(unitPrice, amount, user);
        this.imageQuality = imageQuality;
    }

    @Override
    public BigDecimal getDiscount() {
        BigDecimal total = unitPrice.multiply(amount);
        BigDecimal finalPrice = total.multiply(BigDecimal.valueOf(imageQuality));
        return total.subtract(finalPrice);
    }

    @Override
    public BigDecimal getPrice() {
        return unitPrice.multiply(amount).multiply(BigDecimal.valueOf(imageQuality));
    }

}
