package servlet.filter;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;

        String role = getRole(req);
        req.setAttribute("role", role);
        if (req.getRequestURI().contains("admin") && !role.equalsIgnoreCase("admin")) {
            servletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        }
        if (req.getRequestURI().contains("user")
                && !(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user"))) {
            servletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getRole(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        if (nonNull(session)
                && nonNull(session.getAttribute("email"))
                && nonNull(session.getAttribute("password"))) {
            // Logged user
            return (String) session.getAttribute("role");
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = userService.getByEmailPassword(email, password);
            if (nonNull(user)) {
                session.setAttribute("email", email);
                session.setAttribute("password", password);
                session.setAttribute("role", user.getRole());
                return user.getRole();
            } else {
                return "unknown";
            }
        }
    }

    @Override
    public void destroy() {

    }
}
