package ru.aston.ian_ev.task4.dao;

import ru.aston.ian_ev.task4.connection.ConnectionService;
import ru.aston.ian_ev.task4.dao.mappers.OrderRowMapper;
import ru.aston.ian_ev.task4.dao.mappers.UserRowMapper;
import ru.aston.ian_ev.task4.models.Order;
import ru.aston.ian_ev.task4.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO implements DaoEntityLayer<User> {

    @Override
    public List<User> findAll() {
        ResultSet rs;
        List<User> result;
        try {
            rs = ConnectionService.getConnection().prepareStatement("SELECT * FROM users").executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                result.add(UserRowMapper.getUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public User findEntityById(int id) {
        User user;
        try {
            PreparedStatement preparedStatement =
                    ConnectionService.getConnection().prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            user = UserRowMapper.getUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        int rowsChanged = 0;
        try {
            PreparedStatement preparedStatement =
                    ConnectionService.getConnection().prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            rowsChanged = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return rowsChanged > 0;
    }

    @Override
    public boolean create(User user) {
        int id = -1;
        try {
            PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(
                    "INSERT INTO users(name, surname, email, phone_number) VALUES ( ?, ?, ?, ? ) ", Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return id > 0;
    }

    @Override
    public User update(User user) {
        int rowsChanged = -1;
        try {
            PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(
                    "UPDATE users SET name=?, surname=?, email=?, phone_number=? WHERE id=?"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getId());
            rowsChanged = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        if (rowsChanged < 0) {
            throw new RuntimeException();
        }
        return user;
    }

    public List<User> getUsersWithOrders() {
        List<User> users;
        try {
            ResultSet rs = ConnectionService.getConnection().prepareStatement(
                    "SELECT * " +
                            "FROM orders o " +
                            "LEFT JOIN users u ON o.user_id = u.id "
            ).executeQuery();

            Map<Integer, User> map = new HashMap<>();
            while (rs.next()) {
                Order order = OrderRowMapper.getOrder(rs, "orders.id");
                User user;
                if (map.containsKey(order.getUserId())) {
                    user = map.get(order.getUserId());
                } else {
                    user = UserRowMapper.getUser(rs, "users.id");
                    map.put(order.getUserId(), user);
                }
                user.getOrders().add(order);
            }
            users = new ArrayList<>(map.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
