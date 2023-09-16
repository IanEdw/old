package ru.aston.ian_ev.task4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.ian_ev.task4.dao.UserDAO;
import ru.aston.ian_ev.task4.models.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private static UserDAO underTest;

    @BeforeAll
    static void beforeAll() {
        underTest = new UserDAO();
    }

    @BeforeEach
    void setUp() {
        Utils.executeSqlScripts("sql/schema.sql");
        Utils.executeSqlScripts("sql/data.sql");
    }

    @AfterEach
    void tearDown() {
        Utils.executeSqlScripts("sql/drop.sql");
    }

    @Test
    void expectScriptsExecute() {
        List<User> users = underTest.findAll();

        assertEquals(2, users.size());
    }

    @Test
    void getUserByIdExpectUser() {
        User user = underTest.findEntityById(1);

        assertEquals("Edward", user.getName());
    }

    @Test
    void createUserExpectCreatingUser() {
        User user = new User();
        user.setName("newUser");
        user.setSurname("newSurname");
        user.setEmail("new@email.ru");
        user.setPhoneNumber("123123123");

        assertTrue(underTest.create(user));
        assertEquals("newUser", underTest.findEntityById(3).getName());
    }

    @Test
    void updateUserExpectUpdatedUser() {
        User user = underTest.findEntityById(1);
        user.setName("anotherName");

        underTest.update(user);
        user = underTest.findEntityById(1);

        assertEquals("anotherName", user.getName());
    }

    @Test
    void getUsersWithOrdersExpectOrdersInUserList() {
        List<User> users = underTest.getUsersWithOrders();

        assertFalse(users.get(0).getOrders().isEmpty());
        assertFalse(users.get(1).getOrders().isEmpty());
    }

    @Test
    void deleteByIdExpectDelete() {
        assertTrue(underTest.delete(1));
        assertThrows(RuntimeException.class, () -> underTest.findEntityById(1));
    }
}
