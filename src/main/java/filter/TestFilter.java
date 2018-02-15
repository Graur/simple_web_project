package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/ListUsersServlet",
        filterName = "AdminFilter",
        description = "Filter all admin URLs" )

//@WebFilter(servletNames = "ListUsersServlet")
public class TestFilter implements Filter {

    public TestFilter() {

    }

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        System.out.println("init of TestFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println("Requested Resource::" + uri);

        HttpSession session = req.getSession();
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        System.out.println("Фильтр аутентификации, пользователь::" + login + " " + password);

        if(login.equals("admin")){
            System.out.println("Admin");
            chain.doFilter(req, resp);
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ListUsersServlet");
//            requestDispatcher.forward(req, resp);
        } else {
            System.out.println("User");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user.jsp");
            requestDispatcher.forward(req, resp);

        }
    }

    @Override
    public void destroy() {

    }
}
