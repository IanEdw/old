package ru.aston.ian_ev.task4.dao.mappers;

import ru.aston.ian_ev.task4.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRowMapper {
    public static User getUser(ResultSet rs) {
        return getUser(rs, "id");
    }
    public static User getUser(ResultSet rs, String idRowName) {
        User user = new User();
        try {
            user.setId(rs.getInt(idRowName));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setOrders(new ArrayList<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
