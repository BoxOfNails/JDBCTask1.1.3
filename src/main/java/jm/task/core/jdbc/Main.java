package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        List<User> listOfUsers;
        userDao.createUsersTable();

        userDao.saveUser("Trunk", "Goldstein", (byte) 67);
        userDao.saveUser("Polvina", "Bern", (byte) 50);
        userDao.saveUser("Ivan", "Ivanovich", (byte) 109);
        userDao.saveUser("Cole", "Berenberg", (byte) 27);

        userDao.removeUserById(1);
        listOfUsers = userDao.getAllUsers();
        System.out.println(listOfUsers);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
