package servlets;

import dao.UsersDAO;
import dao.UsersDaoFactory;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet ("/")
public class ListUsersServlet extends HttpServlet {
    private UsersDAO usersDAO = UsersDaoFactory.getUsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<User> userList = usersDAO.getAllUsers();
            req.setAttribute("listUsers", userList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("UsersList.jsp");
            requestDispatcher.forward(req, resp);
    }
}
