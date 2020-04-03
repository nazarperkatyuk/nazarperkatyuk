package ShopProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ShopProject.ConnectionUtil;
import ShopProject.entities.User;

public class UserDao implements CRUD<User> {
    public static final String SELECT_ALL = "SELECT * FROM users";
    public static final String DELETE = "DELETE FROM users where id = ?";
    public static final String UPDATE = "UPDATE users SET email = ?, first_name = ?, last_name = ?, role = ?, password = ? where id = ?";
    public static final String SELECT_BY_EMAIL = "SELECT * FROM users where email = ?";
    public static final String SELECT_BY_ID = "SELECT * FROM users where id = ?";
    public static final String INSERT_INTO =
            "INSERT INTO users(email, first_name, last_name, role, password) values (?, ?, ?, ?, ?)";

    private Connection connection;

    public UserDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public User read(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return User.of(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user by id= " + id);
        }
    }

    @Override
    public List<User> readAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(User.of(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user");
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting user");
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setObject(1, user.getEmail());
            preparedStatement.setObject(2, user.getFirstName());
            preparedStatement.setObject(3, user.getLastName());
            preparedStatement.setObject(4, user.getRole());
            preparedStatement.setObject(5, user.getPassword());
            preparedStatement.setObject(6, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while updating user");
        }
    }

    @Override
    public User create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setObject(4, user.getRole());
            preparedStatement.setObject(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting a user", e);
        }
    }

    public Optional<User> getByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setObject(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(User.of(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user by email= " + email);
        }
    }
}
