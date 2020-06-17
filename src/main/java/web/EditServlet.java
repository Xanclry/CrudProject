package web;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            deleteUser(req, resp);
        }
        if ("create".equals(action)) {
            createUser(req, resp);
        }

    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.deleteUser(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/CrudProject1_1_4_war/edit");
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.addUser(new User(email, password));
        resp.sendRedirect("/CrudProject1_1_4_war/edit");
    }
}
