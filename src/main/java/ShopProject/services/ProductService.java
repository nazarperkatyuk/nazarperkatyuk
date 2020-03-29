package ShopProject.services;

import java.util.List;
import java.util.Set;

import ShopProject.dao.ProductDao;
import ShopProject.entities.Product;

public class ProductService {
    private static ProductService productService;
    private ProductDao productDao;

    private ProductService() {
        this.productDao = new ProductDao();
    }

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public Product create(Product t) {
        return productDao.create(t);
    }

    public Product read(int id) {
        return productDao.read(id);
    }

    public void update(Product t) {
        productDao.update(t);
    }

    public void delete(Integer id) {
        productDao.delete(id);
    }

    public List<Product> readAll() {
        return productDao.readAll();
    }

    public List<Product> readByIds(Set<Integer> productIds) {
        return productDao.readByIds(productIds);
    }
}