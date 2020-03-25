package ShopProject.servlet;

import ShopProject.entities.User;
import ShopProject.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> user = userService.readByEmail(email);
        if (user == null || email.isEmpty() && password.isEmpty()){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        if(user.getPassword().equals(password)){
            req.setAttribute("userEmail", email);
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}