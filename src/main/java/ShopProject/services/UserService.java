package ShopProject.services;

import ShopProject.dao.UserDao;
import ShopProject.entities.User;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService userService;
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User create(User t) {
        return userDao.create(t);
    }

    public Optional<User> readById(int id) {
        return userDao.read(id);
    }

    public Optional<User> readByEmail(String email) {
        return userDao.readByEmail(email);
    }

    public void update(User t) {
        userDao.update(t);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public Optional<List<User>> readAll() {
        return userDao.readAll();
    }
}