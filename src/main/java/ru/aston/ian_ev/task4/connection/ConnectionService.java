package ru.aston.ian_ev.task4.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConnectionService {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
                connection = DriverManager.getConnection(
                        resourceBundle.getString("datasource.url"),
                        resourceBundle.getString("datasource.username"),
                        resourceBundle.getString("datasource.password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
