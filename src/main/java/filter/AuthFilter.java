package filter;

import dao.UsersDAO;
import dao.UsersDaoFactory;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private UsersDAO usersDAO = UsersDaoFactory.getUsersDAO();
    private List<User> userList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("start doFilter");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        if ((session.getAttribute("login") != null) && (session.getAttribute("password") != null)){
            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            moveToMenu(req, resp, role);
        } else if (userIsExist(login, password)){
            final User.ROLE role = getRoleByLoginAndPass(login, password);
            moveToMenu(req, resp, role);
        } else {
            moveToMenu(req, resp, User.ROLE.UNKNOWN);
        }
        System.out.println("end doFilter");
    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp, User.ROLE role) throws ServletException, IOException {
        if (role.equals(User.ROLE.ADMIN)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/UsersList.jsp");
            requestDispatcher.forward(req, resp);
            System.out.println("filter role ADMIN");
        } else if (role.equals(User.ROLE.USER)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user.jsp");
            requestDispatcher.forward(req, resp);
            System.out.println("filter role User");
        } else  {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
            System.out.println("filter role Unknown");
        }
    }

    private boolean userIsExist (String login, String password){
        boolean result = false;

        userList = usersDAO.getAllUsers();

        for (User user : userList){
            if((user.getLogin().equals(login)) && (user.getPassword().equals(password))){
                result = true;
                break;
            }
        }

        return  result;
    }

    private User.ROLE getRoleByLoginAndPass(String login, String password){
        User.ROLE result = User.ROLE.UNKNOWN;

        userList = usersDAO.getAllUsers();

        for (User user : userList){
            if((user.getLogin().equals("admin")) && (user.getPassword().equals("admin"))){
                result = User.ROLE.ADMIN;
            } else if (!(user.getLogin().isEmpty()) && (!(user.getPassword().isEmpty()))){
                result = User.ROLE.USER;
                usersDAO.insertUser(new User(login, password));
            }
        }

        return result;
    }

    @Override
    public void destroy() {

    }
}
