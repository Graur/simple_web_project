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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("start doFilter");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(User.ROLE.ADMIN.toString())){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin");
                requestDispatcher.forward(req, resp);
                System.out.println("filter role ADMIN");
                System.out.println(User.ROLE.ADMIN.toString());
            } else if (cookie.getName().equals(User.ROLE.USER.toString())){
                chain.doFilter(request, response);
                System.out.println("filter role User");
                System.out.println(User.ROLE.USER.toString());
            }
        }
    }

    @Override
    public void destroy() {

    }
}
