package ShopProject.servlet;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ShopProject.entities.Bucket;
import ShopProject.services.BucketService;

@WebServlet("/api/buckets")
public class BucketController extends HttpServlet {

    private BucketService bucketService = BucketService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("productId");
        int userId = (int) req.getSession().getAttribute("userId");

        bucketService.create(new Bucket(userId, Integer.parseInt(productId), new Date()));
    }
}
