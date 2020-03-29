package ShopProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ShopProject.entities.Product;
import ShopProject.services.ProductService;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product product = productService.read(Integer.parseInt(productId));
        request.setAttribute("productName", product.getName());
        request.setAttribute("productD", product.getDescription());
        request.setAttribute("productP", product.getPrice());
        request.setAttribute("productId", product.getId());

        request.getRequestDispatcher("singleProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doDelete(req, resp);
    }

}
