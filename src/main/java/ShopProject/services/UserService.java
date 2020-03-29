package ShopProject.services;

import java.util.List;
import java.util.Optional;

import ShopProject.dao.UserDao;
import ShopProject.entities.User;

public class UserService {

    private UserDao userDao;

    private static UserService userService;

    private UserService() {
        this.userDao = new UserDao();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void create(String email, String firstName, String lastName, String password) {
        userDao.create(
                User.builder()
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setPassword(password)
                        .build());
    }

    public User read(int id) {
        return userDao.read(id);
    }

    public void update(User t) {
        userDao.update(t);
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public List<User> readAll() {
        return userDao.readAll();
    }

    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userDao.getByEmail(email).filter(user -> user.getPassword().equals(password));
    }
}