package ShopProject.servlet;

import ShopProject.entities.User;
import ShopProject.entities.User_Role;
import org.apache.commons.lang3.ObjectUtils;
import ShopProject.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (ObjectUtils.allNotNull(firstName, lastName, email, password)) {
            userService.create(new User(firstName, lastName, email, User_Role.USER.toString(), password));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            return;
        }
        resp.setContentType("text/plain");
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}