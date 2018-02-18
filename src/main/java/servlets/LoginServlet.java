package servlets;

import dao.UsersDAO;
import dao.UsersDaoFactory;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/auth")
public class LoginServlet extends HttpServlet {
    private UsersDAO usersDAO = UsersDaoFactory.getUsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if(userIsExist(login, pass)){
            Cookie cookie = new Cookie("userCookie",login);
            resp.addCookie(cookie);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/auth");
            requestDispatcher.forward(req, resp);
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
}
