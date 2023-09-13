package ru.aston.ian_ev.task5.service;

import ru.aston.ian_ev.task4.dao.UserDAO;
import ru.aston.ian_ev.task4.models.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(int id) {
        return userDAO.findEntityById(id);
    }

    public boolean deleteUserById(int id) {
        return userDAO.delete(id);
    }

    public boolean createUser(User user) {
        return userDAO.create(user);
    }

    public User updateUser(User user) {
        return userDAO.update(user);
    }

    public List<User> getUsersWithOrders() {
        return userDAO.getUsersWithOrders();
    }
}
