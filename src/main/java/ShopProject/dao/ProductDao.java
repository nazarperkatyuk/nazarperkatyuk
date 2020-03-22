package ShopProject.dao;

import ShopProject.entities.Product;
import ShopProject.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao implements CRUD<Product> {


    public ProductDao() {
        this.connection = ConnectionUtil.getConnection();
    }
    private Connection connection;
    private static String SELECT_ALL = "select * from products";
    private static String CREATE = "insert into products(`name`, `description`, `price`) values (?,?,?)";
    private static String READ_BY_ID = "select * from products where id =?";
    private static String UPDATE_BY_ID = "update products set name=?, description = ?, price = ? where id = ?";
    private static String DELETE_BY_ID = "delete from products where id=?";


    @Override
    public Product create(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPurchasePrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            product.setId(generatedKeys.getInt(1));

            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t create new product");
        }
    }



    @Override
    public Optional<Product> read(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return Product.of(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t read a product by id");
        }
    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPurchasePrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t update a product");
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t delete a product by id");
        }

    }

    @Override
    public List<Product> readAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(Product.of(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t read all products");
        }
    }
}