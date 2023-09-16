package ru.aston.ian_ev.task4.dao.mappers;

import ru.aston.ian_ev.task4.models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper {
    public static Order getOrder(ResultSet rs) {
        return getOrder(rs, "id");
    }

    public static Order getOrder(ResultSet rs, String idRowName) {
        Order order = new Order();
        try {
            order.setId(rs.getInt(idRowName));
            order.setAmount(rs.getInt("amount"));
            order.setUserId(rs.getInt("user_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
