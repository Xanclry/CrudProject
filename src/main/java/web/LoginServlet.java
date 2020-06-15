package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getAttribute("role");
        System.out.println(role);
        if (!"Unknown".equals(role)) {
            if (role.equalsIgnoreCase("user")) {
//                req.getRequestDispatcher("/user").forward(req, resp);
                resp.sendRedirect("/CrudProject_war/user");
            }
            if (role.equalsIgnoreCase("admin")) {
//                req.getRequestDispatcher("/admin").forward(req, resp);
                resp.sendRedirect("/CrudProject_war/admin");

            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
