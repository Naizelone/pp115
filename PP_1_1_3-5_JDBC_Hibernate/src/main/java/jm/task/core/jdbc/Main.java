package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Василий","Петров", (byte) 40);
        userService.saveUser("Виктор","Сидоров", (byte) 30);
        userService.saveUser("Владимир","Иванов", (byte) 70);
        userService.saveUser("Вадим","Смирнов", (byte) 50);
        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.dropUsersTable();


    }
}
