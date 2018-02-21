package servlets;

import dao.UsersDAO;
import dao.UsersDaoFactory;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsersDAO usersDAO = UsersDaoFactory.getUsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        Cookie[] cookies = req.getCookies();
        Cookie cookie;

        if(userIsExist(login, pass)){
            User.ROLE userRole = getRoleByLoginAndPass(login, pass);

            for (Cookie delCookie : cookies){
                if (delCookie.getName().equals(User.ROLE.USER.toString()) || delCookie.getName().equals(User.ROLE.ADMIN.toString())) {
                    delCookie.setValue("");
                    delCookie.setPath("/");
                    delCookie.setMaxAge(0);
                    resp.addCookie(delCookie);
                }
            }

            cookie = new Cookie(userRole.toString(), login);
            System.out.println("login: " + userRole.toString());
            resp.addCookie(cookie);
            resp.sendRedirect("/admin");
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }

    private boolean userIsExist (String login, String password){
        boolean result = false;

        List<User> userList = usersDAO.getAllUsers();

        for (User user : userList){
            if((user.getLogin().equals(login)) && (user.getPassword().equals(password))){
                result = true;
                break;
            }
        }

        return  result;
    }

    private User.ROLE getRoleByLoginAndPass(String login, String password){
        User.ROLE result;

        if((login.equals("admin")) && (password.equals("admin"))){
            result = User.ROLE.ADMIN;

        } else {
            result = User.ROLE.USER;
            usersDAO.insertUser(new User(login, password));

        }

        return result;
    }
}
