package ShopProject.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ShopProject.ConnectionUtil;
import ShopProject.entities.Bucket;

public class BucketDao implements CRUD<Bucket> {
    private static final Logger LOG = Logger.getLogger(BucketDao.class);

    private static String READ_ALL = "select * from buckets";
    private static String READ_ALL_BY_USER_ID = "select * from buckets where user_id = ?";
    private static String CREATE = "insert into buckets(`user_id`, `product_id`, `purchase_date`) values (?,?,?)";
    private static String READ_BY_ID = "select * from buckets where id=?";
    private static String DELETE_BY_ID = "delete from buckets where id=?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BucketDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public Bucket create(Bucket bucket) {
        String message = String.format("Will create a bucket for userId=%d and productId=%d",
                bucket.getUserId(), bucket.getProductId());
        LOG.debug(message);

        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            bucket.setId(rs.getInt(1));
        } catch (SQLException e) {
            String errorMessage = String.format("Fail to create a bucket for userId=%d and productId=%d",
                    bucket.getUserId(), bucket.getProductId());
            LOG.error(errorMessage, e);
        }
        return bucket;
    }

    @Override
    public Bucket read(int id) {
        Bucket bucket = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            bucket = Bucket.of(result);
        } catch (SQLException e) {
            String errorMessage = String.format("Fail to get a bucket with id=%d", id);
            LOG.error(errorMessage, e);
        }

        return bucket;
    }

    @Override
    public void update(Bucket t) {
        throw new IllegalStateException("there is no update for bucket");
    }

    @Override
    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bucket> readAll() {

        List<Bucket> bucketRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                bucketRecords.add(Bucket.of(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bucketRecords;
    }

    public List<Bucket> readAllByUserId(int userId) {

        List<Bucket> bucketRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                bucketRecords.add(Bucket.of(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bucketRecords;
    }
}
