package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user", "/admin"},
        description = "Filter all admin URLs")
public class UsersFilter implements Filter {
    FilterConfig fConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig == null) {
            this.fConfig = filterConfig;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("start doFilter");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(User.ROLE.ADMIN.toString())){
                System.out.println("filter role ADMIN");
                System.out.println(User.ROLE.ADMIN.toString());
                chain.doFilter(req, resp);
                return;
            } else if ((cookie.getName().equals(User.ROLE.USER.toString())) || (cookie.getName().equals(User.ROLE.ADMIN.toString()))){
                System.out.println("filter role User");
                System.out.println(User.ROLE.USER.toString());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user");
                requestDispatcher.forward(req, resp);
                return;
            }
        }
        resp.sendRedirect("Error.jsp");
    }

    @Override
    public void destroy() {
        fConfig = null;
    }
}
