package ShopProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ShopProject.ConnectionUtil;
import ShopProject.entities.Product;

public class ProductDao implements CRUD<Product> {
    private static String READ_ALL = "select * from products";
    private static String READ_ALL_IN = "select * from products where id in ";
    private static String CREATE = "insert into products(`name`, `description`, `price`) values (?,?,?)";
    private static String READ_BY_ID = "select * from products where id =?";
    private static String UPDATE_BY_ID = "update products set name=?, description = ?, price = ? where id = ?";
    private static String DELETE_BY_ID = "delete from products where id=?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public Product create(Product product) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product read(int id) {
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            product = Product.of(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void update(Product product) {

        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public List<Product> readAll() {
        List<Product> productRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                productRecords.add(Product.of(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productRecords;
    }

    public List<Product> readByIds(Set<Integer> productIds) {
        List<Product> productRecords = new ArrayList<>();
        try {

            String ids = productIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            String query = String.format("%s (%s)", READ_ALL_IN, ids);
            preparedStatement = connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                productRecords.add(Product.of(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productRecords;
    }
}
