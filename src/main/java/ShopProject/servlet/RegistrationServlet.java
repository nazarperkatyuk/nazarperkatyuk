package ShopProject.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;

import ShopProject.services.UserService;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (ObjectUtils.allNotNull(firstName, lastName, email, password)) {
            userService.create(email, firstName, lastName, password);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return;
        }
        response.setContentType("text/plain");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}