package servlets;

import dao.UsersDAO;
import dao.UsersDaoFactory;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("start LoginServlet");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UsersDAO usersDAO = UsersDaoFactory.getUsersDAO();
        User user = new User(login, password);
        usersDAO.insertUser(user);
        System.out.println("end LoginServlet");
    }
}
