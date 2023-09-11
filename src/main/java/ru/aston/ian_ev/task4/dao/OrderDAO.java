package ru.aston.ian_ev.task4.dao;

import ru.aston.ian_ev.task4.connection.ConnectionService;
import ru.aston.ian_ev.task4.dao.mappers.OrderRowMapper;
import ru.aston.ian_ev.task4.models.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DaoEntityLayer<Order> {

    @Override
    public List<Order> findAll() {
        ResultSet rs;
        List<Order> result;
        try {
            rs = ConnectionService.getConnection().prepareStatement("SELECT * FROM orders").executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                result.add(OrderRowMapper.getOrder(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Order findEntityById(int id) {
        Order order;
        try {
            PreparedStatement preparedStatement =
                    ConnectionService.getConnection().prepareStatement("SELECT * FROM orders WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            order = OrderRowMapper.getOrder(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public boolean delete(int id) {
        int rowsChanged = 0;
        try {
            PreparedStatement preparedStatement =
                    ConnectionService.getConnection().prepareStatement("DELETE FROM orders WHERE id=?");
            preparedStatement.setInt(1, id);
            rowsChanged = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return rowsChanged > 0;
    }

    @Override
    public boolean create(Order order) {
        int id = -1;
        try {
            PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(
                    "INSERT INTO orders(amount, user_id) VALUES ( ?, ? )", Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, order.getAmount());
            preparedStatement.setInt(2, order.getUserId());
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
    public Order update(Order order) {
        int rowsChanged = -1;
        try {
            PreparedStatement preparedStatement = ConnectionService.getConnection().prepareStatement(
                    "UPDATE orders SET amount=?, user_id=? WHERE id=?"
            );
            preparedStatement.setInt(1, order.getAmount());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getId());
            rowsChanged = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        if (rowsChanged < 0) {
            throw new RuntimeException();
        }
        return order;
    }
}
