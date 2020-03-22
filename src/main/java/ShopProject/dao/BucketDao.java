package ShopProject.dao;

import ShopProject.entities.Bucket;
import ShopProject.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDao implements CRUD <Bucket> {
    private Connection connection;

    public BucketDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    public static final String SELECT_ALL = "SELECT * FROM buckets";
    public static final String DELETE = "DELETE FROM buckets where id = ?";
    public static final String UPDATE = "UPDATE buckets SET user_id = ?, product_id = ?, purchase_date = ? where id = ?";
    public static final String SELECT_BY_ID = "SELECT * FROM buckets where id = ?";
    public static final String INSERT_INTO =
            "INSERT INTO buckets(user_id, product_id, purchase_date) values(?, ?, ?)";
    @Override
    public Bucket create(Bucket bucket) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bucket.getUser_id());
            preparedStatement.setInt(2, bucket.getProduct_id());
            preparedStatement.setDate(3, (Date) bucket.getPurchase_date());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            bucket.setId(generatedKeys.getInt(1));

            return bucket;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t create new bucket");
        }
    }

    @Override
    public Bucket read(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return Bucket.of(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t read a bucket by id");
        }
    }

    @Override
    public void update(Bucket bucket) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, bucket.getUser_id());
            preparedStatement.setInt(2, bucket.getProduct_id());
            preparedStatement.setDate(3, (Date) bucket.getPurchase_date());
            preparedStatement.setInt(4, bucket.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Can`t update a bucket");
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t delete a bucket by id");
        }
    }

    @Override
    public List<Bucket> readAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<Bucket> buckets = new ArrayList<>();
            while (resultSet.next()) {
                buckets.add(Bucket.of(resultSet));
            }
            return buckets;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t read all buckets");
        }
    }
}