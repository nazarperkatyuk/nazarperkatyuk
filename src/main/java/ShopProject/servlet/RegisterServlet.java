package ShopProject.servlet;

import ShopProject.dao.UserDao;
import ShopProject.entities.User;
import ShopProject.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String password = req.getParameter("password");


        if (firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && password.isEmpty() && role.isEmpty()){
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            userService.create(new User(firstName, lastName, email, role, password));
            req.setAttribute("userEmail", email);
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}