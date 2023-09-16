package ru.aston.ian_ev.task4;

import ru.aston.ian_ev.task4.connection.ConnectionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class Utils {
    public static void executeSqlScripts(String fileName) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(UserDAOTest.class.getClassLoader().getResource(fileName)).openStream()
                )
        )) {
            StringBuilder sb = new StringBuilder();
            while (br.ready()) {
                sb.append(br.readLine());
            }

            for (String s : sb.toString().split(";")) {
                if (!s.isEmpty()) {
                    Connection connection = ConnectionService.getConnection();
                    connection.createStatement().execute(s);
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
