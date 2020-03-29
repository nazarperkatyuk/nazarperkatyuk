package ShopProject.servlet.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import ShopProject.entities.Product;
import ShopProject.services.ProductService;

@WebServlet("/api/products")
public class ProductController extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = productService.readAll();
        String json = new Gson().toJson(products);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        Optional<String> errorMessage = getErrorMessage(price);
        if (errorMessage.isPresent()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(errorMessage.get());

            return;
        }
        Product product = new Product(name, description, Double.parseDouble(price));
        productService.create(product);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Optional<String> getErrorMessage(String price) {
        if (Strings.isNullOrEmpty(price)) {
            return Optional.of("Price can't be empty");
        }
        try {
            double parsedPrice = Double.parseDouble(price);
            return parsedPrice > 0 ? Optional.empty() : Optional.of("Price can't less then zero");
        } catch (NumberFormatException e) {
            return Optional.of("Price should be numeric");
        }
    }
}
