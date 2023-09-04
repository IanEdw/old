package ru.aston.ian_ev.task1.order.film;

import ru.aston.ian_ev.task1.order.Order;
import ru.aston.ian_ev.task1.user.User;
import ru.aston.ian_ev.task2.BadOrderArgumentsException;

import java.math.BigDecimal;

public class FilmPhotoPrint extends Order {
    private final Bundle bundle;

    public FilmPhotoPrint(BigDecimal unitPrice, BigDecimal amount, User user, Bundle bundle) throws BadOrderArgumentsException {
        super(unitPrice, amount, user);
        this.bundle = bundle;
    }

    @Override
    public BigDecimal getDiscount() {
        BigDecimal total = unitPrice.multiply(amount);
        switch (bundle) {
            case COMMON -> {
                return total.subtract(total.multiply(BigDecimal.valueOf(0.5)));
            }
            case ADVANCED -> {
                return total.subtract(total.multiply(BigDecimal.valueOf(0.8)));
            }
            default -> {
                return BigDecimal.ZERO;
            }
        }
    }

    @Override
    public BigDecimal getPrice() {
        return unitPrice.multiply(amount).subtract(getDiscount());
    }
}
