package ShopProject.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private String description;
    private float purchasePrice;

    public Product(int id, String name, String description, float purchasePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
    }

    public static Product of (ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Float purchasePrice = resultSet.getFloat("purchase_price");

            return new Product(id, name, description, purchasePrice);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Float.compare(product.purchasePrice, purchasePrice) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, purchasePrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}