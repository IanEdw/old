package ru.aston.ian_ev.task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.ian_ev.task4.dao.UserDAO;
import ru.aston.ian_ev.task4.models.User;
import ru.aston.ian_ev.task5.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserDAO userDAO;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDAO = mock(UserDAO.class);
        userService = new UserService(userDAO);
    }

    @Test
    void getUserExpectOnceCallFindUser() {
        when(userDAO.findEntityById(anyInt())).thenReturn(new User());

        User user = userService.getUserById(1);

        assertNotNull(user);
        verify(userDAO, atLeastOnce()).findEntityById(anyInt());
    }

    @Test
    void getAllUsersExpectReturnUsers() {
        when(userDAO.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userDAO, atLeastOnce()).findAll();
    }

    @Test
    void createUserExpectCallCreateUserOnce() {
        when(userDAO.create(any())).thenReturn(true);

        boolean val = userService.createUser(new User());

        assertTrue(val);
        verify(userDAO, atLeastOnce()).create(any());
    }

    @Test
    void deleteUserByIdExpectCallDelete() {
        when(userDAO.delete(anyInt())).thenReturn(true);

        boolean val = userService.deleteUserById(1);

        assertTrue(val);
        verify(userDAO, atLeastOnce()).delete(anyInt());
    }

    @Test
    void updateUserExpectCallUpdateUser() {
        when(userDAO.update(any())).thenReturn(new User());

        User user = userService.updateUser(new User());

        assertNotNull(user);
        verify(userDAO, atLeastOnce()).update(any());
    }

    @Test
    void getUsersWithOrderExpectCallGetUsersWithOrders() {
        when(userDAO.getUsersWithOrders()).thenReturn(List.of(new User(), new User()));

        List<User> result = userService.getUsersWithOrders();

        assertNotNull(result);
        verify(userDAO, atLeastOnce()).getUsersWithOrders();
    }
}
