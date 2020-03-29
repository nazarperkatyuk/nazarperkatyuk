package ShopProject.servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;

import ShopProject.entities.User;
import ShopProject.services.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!isPramsValid(email, password)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<User> user = userService.getByEmailAndPassword(email, password);

        if (user.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("userRole", user.get().getRole());
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private boolean isPramsValid(String email, String password) {
        return ObjectUtils.allNotNull(email, password);
    }
}
